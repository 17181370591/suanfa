package cn.me.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/*
 * 原题：用任意个5, 10, 15, 20, 30, 50求和得到5000，有多少种方法。
 * 顺便求了取得该值的所有方法,5000方法太多,这一步只算到50. 有如下收获：
1、必须用long，使用int会溢出,使用int和使用long的结果正好差了2的32次方，即int的范围大小
2、成员变量数组会自动初始化，比如long为0，boolean为false
3、完全背包的遍历方式是0->N-1下Ci->V，不是0->N+1下0->V再加一堆判断 ，
上面也可以是1->N+1，不管0->N-1更方便，次数一样就行

completeBagDpAndGetWays能打印每一种方法,它用int reachable[sum+1]表示各数是否可达,
用 List<int[sum+1]>[] pathsArray表示的路径,pathsArray[0]是[{0,0....,0,0}],
表示有1个不选任何数字的路径,pathsArray[1->sum]是[],初始化即可.
如果数b等于 数a加当前数,reachable[a]=0表示a不可达,则b不变(因为b还有原有的数据);
reachable[a]=1表示a可达,则reachable[b]=1(这个值可能原本就是1),
然后遍历pathsArray[a]的所有路径,拷贝一份得到path1(这里都是数组,直接修改会修改原数组),
令path1第i个数取值加1,表示选中此数,然后将path1加入pathsArray[b]
*/

public class KindsOfSum {
	// f(x - 1, y) + f(x - 1, y - nums[x - 1])
	/*
	 * 6,50=5,50+5,0
	 */
	private int[] nums = { 5, 10, 15, 20, 30, 50 };
	private int len = nums.length;
	private int sum = 50;
	private long[] res = new long[sum + 1];
	private List<int[]>[] pathsArray = new List[sum + 1];
	private int[] reachable = new int[sum + 1];

	@Test // 测试 统计路径完全背包的动态规划代码
	public void testcompleteBagDp() {
		completeBagDpAndGetWays(len, sum);
		System.out.println(Arrays.toString(res));
		System.out.println(res[sum] + "\tways=" + pathsArray[sum].size());
		for (int[] l : pathsArray[sum]) {
			System.out.println(Arrays.toString(l));
			int s = 0;
			for (int j = 0; j < l.length; j++) {
				s += l[j] * nums[j];
			}
			if (s != sum) {
				System.out.println("ci方法有误");
				return;
			}
		}

	}

	// 完全背包的动态规划代码,并统计所有路径
	public void completeBagDpAndGetWays(int x, int y) {
		reachable[0] = 1;
		res[0] = 1;
		int[] a = new int[len];
		for (int i = 0; i < pathsArray.length; i++) {
			pathsArray[i] = new ArrayList();
		}
		pathsArray[0].add(a);
		for (int i = 0; i < len; i++) {
			for (int j = nums[i]; j <= y; j++) {
				int pre = j - nums[i];
				if (reachable[pre] == 1) {
					res[j] = res[j] + res[pre];
					reachable[j] = 1;
					// 如果pre可达,j的路径=j的路径+pre的路径
					// pathsArray是个数组,每个值paths是个list,paths每个值是path数组,是路径
					List<int[]> paths = pathsArray[pre];
					for (int p = 0; p < paths.size(); p++) {
						int[] path = paths.get(p);
						int[] path1 = path.clone();
						path1[i] += 1;
						pathsArray[j].add(path1);
					}
				}
			}
		}
	}

	// 完全背包的动态规划代码,不统计路径
	public void completeBagDp(int x, int y) {
		res[0] = 1;
		for (int i = 0; i < len; i++) {
			for (int j = nums[i]; j <= y; j++) {
				int pre = j - nums[i];
				res[j] = res[j] + res[pre];
			}
		}
	}

	@Test
	public void testCompleteBagRec() {
		System.out.println(completeBagRec(len, sum));
	}

	// 完全背包的递归代码
	public long completeBagRec(int x, int y) {
		if (y == 0) {
			return 1;
		}
		if (y < 0 || x == 0) {
			return 0;
		}
		int a = y / x;

		long l = 0;
		for (int i = 0; i <= a; i++) {
			l = l + completeBagRec(x - 1, y - nums[x - 1] * i);
		}
		return l;
	}

}
