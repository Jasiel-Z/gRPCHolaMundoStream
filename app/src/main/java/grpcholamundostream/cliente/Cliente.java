package grpcholamundostream.cliente;

import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class Cliente {
    public static void main(String[] args){
        String host = "localhost";
        int puerto = 9002;
        ManagedChannel ch = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();
        //saludarStream(ch);
        leerArchivoStream(ch);

    }

    public static void saludarUnario(ManagedChannel ch){
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("JASIEL").build();
        SaludoResponse respuesta = stub.saludo(peticion);
        System.out.println("Respuesta RPC: " + respuesta.getResultado());
    }

    public static void saludarStream(ManagedChannel ch){
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("JASIEL").build();
        stub.saludoStream(peticion).forEachRemaining(respuesta -> {
            System.out.println("Respuesta RPC: " + respuesta.getResultado());
        });
    }

    public static void leerArchivoStream(ManagedChannel ch){
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("archivote.csv").build();
        stub.enviaArchivoStream(peticion).forEachRemaining(respuesta ->{
            System.out.println(respuesta.getResultado());
        });

    }


}
