attribute vec4 position;
attribute vec2 inputTextureCoordinate;
uniform mat4 modelMatrix;
uniform mat4 projectViewdMatrix;
varying vec2 textureCoordinate;

void main()
{
    gl_Position = projectViewdMatrix * modelMatrix * position ;
    textureCoordinate = inputTextureCoordinate;
}