#version 460 core

layout(location = 0) in vec2 position;
layout(location = 2) in vec2 texCoord;

layout(location = 0) out vec2 fragTexCoord;

void main() {
    gl_Position = vec4(position, 0.0, 1.0);
    fragTexCoord = texCoord;
}
