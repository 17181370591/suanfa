package cn.me.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.me.utils.Fix;

/*计算01背包和完全背包的 最优解的个数和全部的选择方法,两种方法的唯一区别依旧是v到V的遍历方向。
 * 思路同KindsOfSum的completeBagDpAndGetWays方法,只是用Count代替了reachable,
 * Count表示可达的路径数
*/
public class CountAndPathsOf1dBag {
	private int[] C = { 20, 10, 15, 14, 5 };
	private double[] W = { 20, 10, 15, 15, 5 };
	private int V = 51;
	private int N = C.length;
	private int[] Count = new int[V + 1];
	private double[] res = new double[V + 1];
	private List<int[]>[] pathsArray = new ArrayList[V + 1];

	@Before // 统一初始化一些参数
	public void init() {
		Count[0] = 1;
		for (int i = 0; i < pathsArray.length; i++) {
			pathsArray[i] = new ArrayList();
		}
		int[] a = new int[N];
		pathsArray[0].add(a);
	}

	@Test
	public void zeroOneBag() {
		for (int i = 0; i < N; i++) {
			int v = C[i];
			for (int j = V; j >= v; j--) {
				int pre = j - v;
				double max = Fix.fix(Math.max(res[j], res[pre] + W[i]));
				// 如果max != res[j]说明res[j]已有的路径不能到达最大值,已经没有意义,清空信息
				if (max != res[j]) {
					Count[j] = 0;
					pathsArray[j].clear();
				}
				if (max == res[pre] + W[i]) {
					Count[j] += Count[pre];
					List<int[]> paths = pathsArray[pre];
					for (int p = 0; p < paths.size(); p++) {
						int[] path1 = paths.get(p).clone();
						path1[i] += 1;
						pathsArray[j].add(path1);
					}
				}
				res[j] = max;
			}
			System.out.println(Arrays.toString(Count));
			System.out.println(Arrays.toString(res));
		}
		System.out.println(getCount(res[V]));
	}

	@Test
	public void completeBag() {
		for (int i = 0; i < N; i++) {
			int v = C[i];
			for (int j = v; j <= V; j++) {
				int pre = j - v;
				double max = Fix.fix(Math.max(res[j], res[pre] + W[i]));
				if (max != res[j]) {
					Count[j] = 0;
					pathsArray[j].clear();
				}
				if (max == res[pre] + W[i]) {
					Count[j] += Count[pre];
					List<int[]> paths = pathsArray[pre];
					for (int p = 0; p < paths.size(); p++) {
						int[] path1 = paths.get(p).clone();
						path1[i] += 1;
						pathsArray[j].add(path1);
					}
				}
				res[j] = max;
			}
			System.out.println(Arrays.toString(Count));
			System.out.println(Arrays.toString(res));
		}
		System.out.println(getCount(res[V]));
	}

	// 设体积V的背包最大价值是W,如果达到W的最小体积是V2,那么V2->V的所有路径都应该被统计
	// 参数mysum是 价值,可以小于最大价值,此方法可以打印价值=mysum的路径数和所有路径
	public int getCount(double mysum) {
		int i = V;
		int mycount = 0;
		while (i >= 0 && res[i] >= mysum) {
			int x = Count[i];
			if (res[i] == mysum && x > 0) {
				mycount += x;
				printPathsArray(i);
			}
			i -= 1;
		}
		return mycount;
	}

	// 参数x是 背包体积,pathArrays[x]即这个体积取最大价值时的路径的list,
	// 遍历并打印顺便测试结果是否正确
	public void printPathsArray(int x) {
		List<int[]> paths = pathsArray[x];
		for (int i = 0; i < paths.size(); i++) {
			int myv = 0;
			double myw = 0;
			int[] mypath = paths.get(i);
			for (int j = 0; j < mypath.length; j++) {
				myv += mypath[j] * C[j];
				myw += mypath[j] * W[j];
			}
			if (myv == x && Fix.fix(myw) == res[x]) {
				System.out.println("体积" + x + "时选择是:" + Arrays.toString(mypath));
			} else {
				System.out.println("体积" + x + "时出错了");
			}
		}
	}

}
