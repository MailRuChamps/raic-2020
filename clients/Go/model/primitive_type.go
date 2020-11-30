package model

import "io"
import . "aicup2020/stream"

type PrimitiveType int32
const (
    PrimitiveTypeLines PrimitiveType = 0
    PrimitiveTypeTriangles PrimitiveType = 1
)
func ReadPrimitiveType(reader io.Reader) PrimitiveType {
    switch ReadInt32(reader) {
    case 0:
        return PrimitiveTypeLines
    case 1:
        return PrimitiveTypeTriangles
    }
    panic("Unexpected tag value")
}
