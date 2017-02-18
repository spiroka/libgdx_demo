uniform sampler2D u_texture;

varying vec2 v_texCoord;
varying vec4 v_color;
varying float v_red;

void main() {
	gl_FragColor = texture2D(u_texture, v_texCoord) * v_color + vec4(v_red, v_red, v_red, 0.05); 
}	