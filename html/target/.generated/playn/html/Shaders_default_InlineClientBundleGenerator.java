package playn.html;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class Shaders_default_InlineClientBundleGenerator implements playn.html.Shaders {
  private static Shaders_default_InlineClientBundleGenerator _instance0 = new Shaders_default_InlineClientBundleGenerator();
  private void colorFragmentShaderInitializer() {
    colorFragmentShader = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/home/tsmaster/.m2/repository/com/googlecode/playn/playn-core/1.0.1/playn-core-1.0.1.jar!/playn/core/gl/color-fragment-shader.txt
      public String getText() {
        return "#ifdef GL_ES\nprecision highp float;\n#endif\n\nuniform vec4 u_Color;\n\nuniform float u_Alpha;\n\nvoid main(void) {\n  gl_FragColor = vec4(u_Color.rgb, u_Color.a * u_Alpha);\n}\n";
      }
      public String getName() {
        return "colorFragmentShader";
      }
    }
    ;
  }
  private static class colorFragmentShaderInitializer {
    static {
      _instance0.colorFragmentShaderInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return colorFragmentShader;
    }
  }
  public com.google.gwt.resources.client.TextResource colorFragmentShader() {
    return colorFragmentShaderInitializer.get();
  }
  private void texFragmentShaderInitializer() {
    texFragmentShader = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/home/tsmaster/.m2/repository/com/googlecode/playn/playn-core/1.0.1/playn-core-1.0.1.jar!/playn/core/gl/tex-fragment-shader.txt
      public String getText() {
        return "#ifdef GL_ES\nprecision highp float;\n#endif\n\nuniform sampler2D u_Texture;\n\nvarying vec2 v_TexCoord;\n\nuniform float u_Alpha;\n\nvoid main(void) {\n  vec4 textureColor = texture2D(u_Texture, v_TexCoord);\n  gl_FragColor = vec4(textureColor.rgb, textureColor.a * u_Alpha);\n}\n";
      }
      public String getName() {
        return "texFragmentShader";
      }
    }
    ;
  }
  private static class texFragmentShaderInitializer {
    static {
      _instance0.texFragmentShaderInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return texFragmentShader;
    }
  }
  public com.google.gwt.resources.client.TextResource texFragmentShader() {
    return texFragmentShaderInitializer.get();
  }
  private void vertexShaderInitializer() {
    vertexShader = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/home/tsmaster/.m2/repository/com/googlecode/playn/playn-core/1.0.1/playn-core-1.0.1.jar!/playn/core/gl/vertex-shader.txt
      public String getText() {
        return "uniform vec2 u_ScreenSize;\n\nattribute vec4 a_Matrix;\nattribute vec2 a_Translation;\nattribute vec2 a_Position;\nattribute vec2 a_Texture;\n\nvarying vec2 v_TexCoord;\n\nvoid main(void) {\n  // Transform the vertex.\n  mat3 transform = mat3(\n    a_Matrix[0], a_Matrix[1], 0,\n    a_Matrix[2], a_Matrix[3], 0,\n    a_Translation[0], a_Translation[1], 1);\n  gl_Position = vec4(transform * vec3(a_Position, 1.0), 1);\n\n  // Scale from screen coordinates to [0, 2].\n  gl_Position.x /= (u_ScreenSize.x / 2.0);\n  gl_Position.y /= (u_ScreenSize.y / 2.0);\n\n  // Offset to [-1, 1] and flip y axis to put origin at top-left.\n  gl_Position.x -= 1.0;\n  gl_Position.y = 1.0 - gl_Position.y;\n\n  v_TexCoord = a_Texture;\n}\n";
      }
      public String getName() {
        return "vertexShader";
      }
    }
    ;
  }
  private static class vertexShaderInitializer {
    static {
      _instance0.vertexShaderInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return vertexShader;
    }
  }
  public com.google.gwt.resources.client.TextResource vertexShader() {
    return vertexShaderInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.google.gwt.resources.client.TextResource colorFragmentShader;
  private static com.google.gwt.resources.client.TextResource texFragmentShader;
  private static com.google.gwt.resources.client.TextResource vertexShader;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      colorFragmentShader(), 
      texFragmentShader(), 
      vertexShader(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("colorFragmentShader", colorFragmentShader());
        resourceMap.put("texFragmentShader", texFragmentShader());
        resourceMap.put("vertexShader", vertexShader());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'colorFragmentShader': return this.@playn.html.Shaders::colorFragmentShader()();
      case 'texFragmentShader': return this.@playn.html.Shaders::texFragmentShader()();
      case 'vertexShader': return this.@playn.html.Shaders::vertexShader()();
    }
    return null;
  }-*/;
}
