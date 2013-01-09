/* Copyright (c) 2012 Tobias Wolf, All Rights Reserved
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.  
 */
package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.OaIdl.SAFEARRAY;
import com.sun.jna.platform.win32.OaIdl.SAFEARRAYBOUND;
import com.sun.jna.platform.win32.Variant.VARIANT;
import com.sun.jna.platform.win32.WTypes.VARTYPE;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import com.sun.jna.platform.win32.COM.COMException;
import com.sun.jna.platform.win32.COM.COMUtils;

/**
 * @author Tobias Wolf, wolf.tobias@gmx.net
 * 
 */
public abstract class OleAut32Util {

	public static SAFEARRAY createVarArray(int size) {
		VARTYPE arrayType = new VARTYPE(0x2000 | 3);
		SAFEARRAY psa;
		SAFEARRAYBOUND[] rgsabound = new SAFEARRAYBOUND[1];
		rgsabound[0] = new SAFEARRAYBOUND(size, 0);

		psa = OleAut32.INSTANCE.SafeArrayCreate(Variant.VT_VARIANT, 1,
				rgsabound);

		return psa;
	}

	public static void SafeArrayPutElement(SAFEARRAY array, int index,
			VARIANT arg) throws COMException {
		int[] idx = new int[1];
		idx[0] = index;
		HRESULT hr = OleAut32.INSTANCE.SafeArrayPutElement(array, idx, arg.getPointer());
		COMUtils.SUCCEEDED(hr);
	}
}
