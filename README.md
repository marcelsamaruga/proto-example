# Proto Generator (presented at The Developers Conference 2021)
## proto example
It contains:
- Presentation
- Code comparing JSON vs Protobuf
- Code developed in Java using gRPC framework
- There are 4 types of gRPC Apis example: Unary, Server Streaming, Client Streaming and Bi-Directional Streaming

### commands
protoc --proto_path=. --java_out=. tutorial.proto
