/*******************************************************************************
 *    Copyright 2015, 2016 Taylor G Smith
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.clust4j.utils.parallel.map;

import org.apache.commons.math3.util.FastMath;

/**
 * A class for distributed summing of vectors
 * @author Taylor G Smith
 */
@Deprecated
final public class DistributedAbs extends MapTaskOperator {
	private static final long serialVersionUID = -6086182277529660733L;

    private DistributedAbs(final double[] arr, int lo, int hi) {
        super(arr, lo, hi);
    }

    @Override
    protected double operate(final double a) {
    	return FastMath.abs(a);
    }
	
	@Override
	protected DistributedAbs newInstance(final double[] array, final int low, final int high) {
		return new DistributedAbs(array, low, high);
	}
	
	public static double[] operate(final double[] array) {
		return getThreadPool().invoke(new DistributedAbs(array, 0, array.length));
    }
}