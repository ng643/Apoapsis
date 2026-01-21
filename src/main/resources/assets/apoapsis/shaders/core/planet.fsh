#version 150

uniform sampler2D Sampler0;
uniform vec4 ColorModulator;

in vec4 vColor;
in vec2 vUv;

out vec4 fragColor;

void main() {
    fragColor = texture(Sampler0, vUv) * vColor * ColorModulator;
    if (fragColor.a < 0.1) discard;
    gl_FragDepth = 1.0;
}