package grpchelloworld.servidor;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Servidor {
    
    public static void main(String[] args) throws IOException, InterruptedException{
        int puerto = 9001;

        //crear servidor usando proto
        Server servidor = ServerBuilder.forPort(puerto).
            addService(new ServidorImp()).build();

        servidor.start();

        System.out.println("Servidor iniciado");
        System.out.println("Escuchando desde el puerto " + puerto );

        // al recibir solicitud de apagado con ctrl + C
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("Recibiendo solicitud de apagado");
                servidor.shutdown();
                System.out.println("Servidor detenido");
            }
        });

        servidor.awaitTermination();
    }
}
