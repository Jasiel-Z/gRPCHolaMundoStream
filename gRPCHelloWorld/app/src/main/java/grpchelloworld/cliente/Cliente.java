package grpchelloworld.cliente;

import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        String host  = "localhost";
        int puerto = 9001;

        //crear canal de comunicacion
        ManagedChannel ch = ManagedChannelBuilder.forAddress(host, puerto).
        usePlaintext().build();

        //obtener referencia del stub
        SaludoServiceGrpc.SaludoServiceBlockingStub stub = SaludoServiceGrpc.newBlockingStub(ch);
        SaludoRequest peticion = SaludoRequest.newBuilder().setNombre("Jasiel").build();
        SaludoResponse respuesta =  stub.saludo(peticion);

        System.out.println("Respuesta RPC:" + respuesta.getResultado());
        System.out.println("Apagando...");
        ch.shutdown();
    }   
}
