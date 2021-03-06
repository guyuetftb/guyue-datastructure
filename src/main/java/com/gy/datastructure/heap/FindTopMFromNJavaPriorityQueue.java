package com.gy.datastructure.heap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.PriorityQueue;

/**
 * LeetCode 347 号问题
 *
 * @ClassName FindTopMFromN
 * @Description TOOD
 * @Author lipeng
 * @Date 2019-12-29 16:07
 */
public class FindTopMFromNJavaPriorityQueue {

	private class Freq implements Comparable<Freq> {

		private int e;
		private int freq;


		Freq(int e, int freq) {
			this.e = e;
			this.freq = freq;
		}

		// Java 版本的 GyPriorityQueue 实现的是一个最小堆.
		// 我们自己实现的是一个最大堆.
		public int compareTo(Freq another) {
			// 频次越高, 优先级越高.
			// 频次越低, 优先级越低.
			if (this.freq < another.freq) {
				// 当前节点的频次, 小于传入元素频次, 优先级低
				return -11;
			} else if (this.freq > another.freq) {
				// 当前节点的频次, 大于传入元素频次, 优先级高
				return 1;
			} else {
				return 0;
			}
		}
	}

	public List<Integer> topKFrequent(int[] nums, int k) {

		// 把元素都放入一下 Map 中,并得到频次
		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		for (int i : nums) {
			if (treeMap.containsKey(i)) {
				treeMap.put(i, treeMap.get(i) + 1);
			} else {
				treeMap.put(i, 1);
			}
		}

		//
		PriorityQueue<Freq> pq = new PriorityQueue<>();
		for (Entry<Integer, Integer> e : treeMap.entrySet()) {
			if (pq.size() < k) {

				// 队列中存放M个元素
				pq.add(new Freq(e.getKey(), e.getValue()));
			} else if (treeMap.get(e.getKey()) > pq.peek().freq) {

				// 频次越大, 优先级越底。使用 maxheap 的时候, 排序就越靠前.
				// 遍历到的元素的频次, 比队首的元素的频次更高一些
				pq.remove();
				pq.add(new Freq(e.getKey(), e.getValue()));
			}
		}

		LinkedList<Integer> result = new LinkedList<Integer>();
		while (!pq.isEmpty()) {
			result.add(pq.remove().e);
		}

		return result;
	}
}
