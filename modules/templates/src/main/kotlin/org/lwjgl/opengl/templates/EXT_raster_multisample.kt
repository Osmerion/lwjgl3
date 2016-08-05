/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.opengl.templates

import org.lwjgl.generator.*
import org.lwjgl.opengl.*

val EXT_raster_multisample = "EXTRasterMultisample".nativeClassGL("EXT_raster_multisample", postfix = EXT) {
	documentation =
		"""
		Native bindings to the $registryLink extension.

		This extension allows rendering to a non-multisample color buffer while rasterizing with more than one sample. The result of rasterization (coverage)
		is available in the {@code gl_SampleMaskIn[]} fragment shader input, multisample rasterization is enabled for all primitives, and several per- fragment
		operations operate at the raster sample rate.

		When using the functionality provided by this extension, depth, stencil, and depth bounds tests must be disabled, and a multisample draw framebuffer
		must not be used.

		A fragment's "coverage", or "effective raster samples" is considered to have "N bits" (as opposed to "one bit" corresponding to the single color
		sample) through the fragment shader, in the sample mask output, through the multisample fragment operations and occlusion query, until the coverage is
		finally "reduced" to a single bit in a new "Coverage Reduction" stage that occurs before blending.
		"""

	IntConstant(
		"Accepted by the {@code cap} parameter of Enable, Disable, IsEnabled.",

		"RASTER_MULTISAMPLE_EXT"..0x9327
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv.",

		"RASTER_SAMPLES_EXT"..0x9328,
		"MAX_RASTER_SAMPLES_EXT"..0x9329,
		"RASTER_FIXED_SAMPLE_LOCATIONS_EXT"..0x932A,
		"MULTISAMPLE_RASTERIZATION_ALLOWED_EXT"..0x932B,
		"EFFECTIVE_RASTER_SAMPLES_EXT"..0x932C
	)

	void(
		"RasterSamplesEXT",
		"""
		Selects the number of samples to be used for rasterization. {@code samples} represents a request for a desired minimum number of samples. Since
		different implementations may support different sample counts, the actual sample pattern used is implementation-dependent. However, the resulting value
		for #RASTER_SAMPLES_EXT is guaranteed to be greater than or equal to {@code samples} and no more than the next larger sample count supported by the
		implementation. If {@code fixedsamplelocations} is GL11#TRUE, identical sample locations will be used for all pixels. The sample locations chosen are a
		function of only the parameters to RasterSamplesEXT and not of any other state.

		If #RASTER_MULTISAMPLE_EXT is enabled, then the sample pattern chosen by RasterSamplesEXT will be used instead of sampling at the center of the pixel.
		The sample locations can be queried with GL32#GetMultisamplefv() with a {@code pname} of GL32#SAMPLE_POSITION, similar to normal multisample sample
		locations.

		The value #MULTISAMPLE_RASTERIZATION_ALLOWED_EXT is GL11#TRUE if GL13#SAMPLE_BUFFERS is one or if #RASTER_MULTISAMPLE_EXT is enabled. The value
		#EFFECTIVE_RASTER_SAMPLES_EXT is equal to #RASTER_SAMPLES_EXT if #RASTER_MULTISAMPLE_EXT is enabled, otherwise is equal to GL13#SAMPLES.

		Explicit multisample rasterization can not be used in conjunction with depth, stencil, or depth bounds tests, multisample framebuffers, or if
		#RASTER_SAMPLES_EXT is zero. If #RASTER_MULTISAMPLE_EXT is enabled, the error $INVALID_OPERATION will be generated by Draw commands if
		${ul(
			"the value of #RASTER_SAMPLES_EXT is zero",
			"the depth, stencil, or depth bounds test is enabled",
			"a multisample draw framebuffer is bound (GL13#SAMPLE_BUFFERS is one)"
		)}

		<h5>Errors</h5>

		An $INVALID_VALUE error is generated if {@code samples} is greater than the value of #MAX_RASTER_SAMPLES_EXT (the implementation-dependent maximum
		number of samples).
		""",

		GLuint.IN("samples", "the number of samples to be used for rasterization"),
		GLboolean.IN("fixedsamplelocations", "if GL11#TRUE, identical sample locations will be used for all pixels")
	)
}