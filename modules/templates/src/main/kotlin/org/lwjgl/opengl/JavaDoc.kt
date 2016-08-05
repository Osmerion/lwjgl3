/* 
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.opengl

import java.io.PrintWriter
import org.lwjgl.generator.*

/** Specifies an alternative name for the OpenGL SDK Reference url. */
class ReferenceGL(val function: String): FunctionModifier() {
	companion object: ModifierKey<ReferenceGL>

	override val isSpecial = false
	override fun validate(func: NativeClassFunction) {
		if ( !func.nativeClass.postfix.isEmpty() )
			throw IllegalArgumentException("The ReferenceGL modifier can only be applied on core functionality.")
	}
}

fun PrintWriter.printOpenGLJavaDoc(documentation: String, function: String, deprecated: Boolean) {
	val link = url("http://www.opengl.org/sdk/docs/man${if ( deprecated ) "2" else ""}/html/$function.xhtml", "OpenGL SDK Reference")
	val injectedJavaDoc =
		if ( deprecated )
			"$link - <em>This function is deprecated and unavailable in the Core profile</em>"
		else
			link

	print("\t/**\n\t * <p>$injectedJavaDoc</p>\n\t * \n")
	if ( documentation.indexOf('\n') == -1 ) {
		print("\t * ")
		println(documentation.substring("\t/** ".length, documentation.length - 3))
		println("\t */")
	} else {
		println(documentation.substring("\t/**\n".length))
	}
}