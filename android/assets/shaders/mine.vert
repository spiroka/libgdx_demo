uniform mat4 u_projTrans;
uniform float u_red;

attribute vec2 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

varying vec2 v_texCoord;
varying vec4 v_color;
varying float v_red;

void main() {
	gl_Position = u_projTrans * vec4(a_position, 0.0, 1.0);
	v_texCoord = a_texCoord0;
	v_color = a_color;
	v_red = u_red;
}