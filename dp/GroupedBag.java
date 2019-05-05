package cn.me.dp;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.junit.Test;

import cn.me.utils.Fix;

/*
 * 分组背包,zeroOneGroupBag是01分组背包,completeGroupBag是完全分组背包,
 * group用来分组,group[x]表示第x组的第个索引,索引Cy group[x]<=y<group[x+1]
 * 表示第x组的所有物品。
 * 
 * zeroOneGroupBag按照0->K,V->0,组内遍历的方式进行遍历,组内遍历对每一个背包体积遍历组内物品,
 * 遍历完每个物品取最大值更新,很明显不会出现同时选两个组内物品的情况
 * 
 * 
 * 
 * 下面方法有误,例如6:7和3:3会在15造成bug.有大球v1:w1和小球v2:w2,其实只要大球性价比更高,
 *  先遍历大球且v1*a=v2*b且w1*a<w2*(b+1),就会在 v2*(b+1) 处出现bug,
 *  所以把每组球先排序也许可以解决这个问题。
 * completeGroupBag按照0->K,组内遍历,0->V的方式进行遍历,原因是按照zeroOneGroupBag的方式
 * 遍历一定会出现同时选两个组内物品的情况，因为f[j+v]可能用到f[j]的值,f[j]可能包含了组内
 * 的某个球,f[j+v]再加入一个球就会出现bug。即使使用标记也没用,比如球1是2/2,球2是4/5,
 * 生成的数组是0,0,2,0,5,0,5(bug),0,10，解决办法是调整遍历顺序+标记，
 * 对于每一组创建一个长度为N+1的标记数组,初始值-1,对于组内每一个球,遍历0->V,
 * 如果f[j-v]+w比f[j]大,
 * 		如果v的标记是-1,说明这个点没有使用本组的球,用当前球的索引做标记,并更新f[j],
 * 		如果v的标记等于当前球的索引,说明这个点正好使用了当前球,用当前球的索引做标记并更新f[j]
 * 		如果标记不是这两种情况,说明已经加入本组其他的球,不能加上当前球,则不更新索引和f[j],
 * 如果f[j-v]+w小于等于f[j],说明不需要当前球,
 * 		如果f[j-1]>f[j],则使f[j]=f[j-1],用j-1球的标记做标记
 * 		否则不处理
 */
public class GroupedBag {
	private Logger log = Logger.getLogger("x");
	private Deque<Integer> deque = new LinkedList<Integer>();
	private int[] C = { 7, 20, 13, 3, 16, 21, 11, 26, 9, 19 };
	private int N = C.length;
	private double[] W = { 3.6, 13, 6, 1, 7.4, 10.5, 4, 12, 4, 12 };
	private int[] group = { 0, 2, 5, 8 };
	private int V = 60;
	private int K = group.length;
	private double[] f = new double[V + 1];

	@Test // 这是01背包，别再改错地方了。。。。。
	public void zeroOnedGroupBag() {
		for (int i = 0; i < K; i++) {
			for (int j = V; j >= 0; j--) {
				// log.debug("i=" + i + "\tj=" + j);
				// f[i,j]=f[i-1,j-v]+w
				// x表示当前在处理第x个球
				int x = group[i];
				int index = C.length;
				if (i < K - 1) {
					index = group[i + 1];
				}
				double max = f[j];
				while (x < index) {
					// log.debug("i=" + i + "\tj=" + j + "\tx=" + x);
					if (j - C[x] >= 0) {
						max = Fix.fix(Math.max(max, f[j - C[x]] + W[x]));
					}
					x = x + 1;
				}
				f[j] = max;
			}
			log.debug(Arrays.toString(f));
		}
	}

	@Test // 这是才是完全背包,此代码的结果有误
	public void completeGroupBag() {
		for (int i = 0; i < K; i++) {
			int[] which = new int[V + 1];
			for (int j = 0; j <= V; j++) {
				which[j] = -1;
			}
			// x表示当前在处理第x个球
			int x = group[i];
			int index = N;
			if (i < K - 1) {
				index = group[i + 1];
			}
			while (x < index) {
				for (int j = 0; j <= V; j++) {
					double max = f[j];
					if (j - C[x] >= 0) {
						double max1 = f[j - C[x]] + W[x];
						int preIndex = which[j - C[x]];
						if (max1 > max) {
							if (preIndex == -1 || preIndex == x) {
								which[j] = x;
								max = Fix.fix(Math.max(max, f[j - C[x]] + W[x]));
							} else if (f[j - 1] > max) {
								which[j] = which[j - 1];
								max = Fix.fix(f[j - 1]);
							}
						}
					}
					f[j] = max;
				}
				x = x + 1;
			}
			log.debug(Arrays.toString(which));
			log.debug(Arrays.toString(f));
		}
	}

}
