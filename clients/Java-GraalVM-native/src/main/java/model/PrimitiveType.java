package model;

import util.StreamUtil;

public enum PrimitiveType {
    LINES(0),
    TRIANGLES(1);
    public int tag;
    PrimitiveType(int tag) {
      this.tag = tag;
    }
}
