package chapter_9_others;

public class Problem_31_KMPAlgorithm {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0; // 主串下标
		int mj = 0; // 匹配串下标
		int[] next = getNextArray(ms);
		while (si < ss.length && mj < ms.length) {
			if (ss[si] == ms[mj]) {
				si++;
				mj++;
			} else if (next[mj] == -1) { // m0 
				si++; // 不匹配，next值为-1时，si 后移
			} else {
				mj = next[mj]; // 不匹配，（正常情况下）mj 回退
			}
		}
		return mj == ms.length ? si - mj : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1; // 默认
		next[1] = 0; // 默认
		int pos = 2;
		int k = 0; // 记录回退位置值，匹配时自增，未匹配非0时取其对应的next值（即回溯）
		while (pos < next.length) {
			// ms[k]的作用？
			if (ms[pos - 1] == ms[k]) {
				next[pos++] = ++k;
			} else if (k > 0) {
				k = next[k]; // "-" 向前回溯，匹配确定回退位置
			} else {
				next[pos++] = 0; // 空白
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str = "abcabcababaccc";
		String match = "ababacfabd"; 
		//pos-1:     a  b  a  b  a      c  f  b  a  b
	    //next: [-1, 0, 0, 1, 2, 0,     0, 0, 1, 2]
		//k: 0       _     1  2  3  2-1-0, 0  1  2
		/**
		 * m1 b 与 a未匹配上，即next设为0 
		 * m2 a 与 a匹配上了，即next值+1，k +1
		 * m3 b 与 b匹配上了，即next值+1，k +1
		 * m4 a 与 a匹配上了，即next值+1，k +1， k=3
		 * m5 c 与 b未匹配上，k=2,1,0, then next=0
		 * 
		 *  */ 
		System.out.println(getIndexOf(str, match));
	}

	/**
	 * KMP与Next数组
	 * https://juejin.cn/post/7179394946351235127
	 * 
	 */

}
