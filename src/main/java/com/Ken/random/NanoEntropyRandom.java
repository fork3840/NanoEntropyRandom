/*
 * Copyright (c) 2025 [Ken]. All rights reserved.
 * 
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     opensource.org
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
 
package com.Ken.random;

/**
 * <h1>NanoEntropyRandom</h1>
 * 自研高强度伪随机数生成工具库。
 * <p>
 * 本类基于线性同余算法（LCG）并注入纳秒级环境熵（System.nanoTime），
 * 解决了传统随机数生成器易被预测和高频调用下规律性过强的问题。
 * </p>
 * 
 * @author  Ken
 * @version 1.0.0
 * @since   2025-12-26
 * @see     <a href="github.com">https://github.com/fork3840/NanoEntropyRandom/projects?query=is%3Aopen</a>
 */

public class NanoEntropyRandom {
	private long seed;
	
	private final long MULTIPLIED = 0xAAFCDBEABL;
	private final long MASK = (1L << 64) - 1;
	private final long INT = (1L << 31);
	private static final NanoEntropyRandom defaultInstance = new NanoEntropyRandom();
	
	private long PLUS = System.currentTimeMillis() * 0xACDBBDADCDL;

	public static NanoEntropyRandom shared() {
		return defaultInstance;
	}

	
	public NanoEntropyRandom(long seed) {
		this.seed = (System.nanoTime() * seed) & MASK;
	}
	
	public NanoEntropyRandom() {
		this.seed = ((System.nanoTime() * 0xAAABDL)) & MASK;
	}
	
	public int nextInt() {
		long time = System.nanoTime();
		this.seed = (this.seed * MULTIPLIED + PLUS) & MASK;
		this.PLUS = (this.PLUS + (time & 0xFF));
		long mixed = (this.seed >>> 16) ^ (time << 13) ^ time;
		int total = (int) ((mixed & 0x7FFFFFFF) % INT);
		total ^= (total >>> 10) & 1; 
		return total;
	}
	
	public boolean nextBoolean() {
		boolean total = (this.nextInt() % 2 == 0);
		return total;
	}
	
	public long nextLong() {
		return ((long)nextInt() << 32) | (nextInt() & 0xFFFFFFFFL);
	}
	
	public double nextDouble() {
		double total =((double)this.nextLong() / (double)Long.MAX_VALUE);
		return total;
	}
	
	public float nextFloat() {
		float total = ((float)this.nextInt() / (float)(INT));
		return total;
	}
	
	public boolean weightRandom(double weight) {
		boolean total = false;
		if (this.nextDouble() < weight) {
			total = true;
		}
		return total;
	}
	
	public int weightRandomArr(int[] weight) {
		int size = weight.length;
		int totalWeight = 0;
		double total = 0;
		for (int i = 0; i < size; i++) {
			totalWeight += weight[i];
		}
		double random = this.nextDouble() * totalWeight;
		for (int i = 0; i < size; i++) {
			total += weight[i];
			if (random < total) {
				return i;
			}
		}
		return size - 1;
	}
	
	public String[] shuffle(String[] arrayNotShuffle) {
		int size = arrayNotShuffle.length;
		String[] arrayShuffle = arrayNotShuffle.clone();
		for (int i = 0; i < size; i++) {
			int random = (this.nextInt() & 0x7FFFFFFF) % (i + 1);
			String temp = arrayShuffle[i];
			arrayShuffle[i] = arrayShuffle[random];
			arrayShuffle[random] = temp;
		}
		return arrayShuffle;
	}
	
	public String randomString(int size) {
		String allWords = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		return randomStringCustom(size, allWords);
	}
	
	public String randomStringCustom(int size, String charpool) {
		if (size <= 0) return "";
		if (charpool == null || charpool.isEmpty()) return "";
		StringBuilder sb = new StringBuilder(size);
		int len = charpool.length();
		for (int i = 0; i < size; i++) {
			int index = (this.nextInt() & 0x7FFFFFFF) % len;
			char c = charpool.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
}