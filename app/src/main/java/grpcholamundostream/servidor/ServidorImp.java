package grpcholamundostream.servidor;

import java.util.Scanner;

import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;

import io.grpc.stub.StreamObserver;



public class ServidorImp extends SaludoServiceGrpc.SaludoServiceImplBase {
    @Override
    public void saludo (SaludoRequest request, StreamObserver<SaludoResponse> respObserver){
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola + "+ request.getNombre()).build();
        respObserver.onNext(respuesta);

        respObserver.onCompleted();

    }

    @Override
    public void saludoStream(SaludoRequest request, StreamObserver<SaludoResponse> resObserver){
        for(int i = 0; i < 10; i++){
            int numeroSaludo = i + 1;
            SaludoResponse respuesta = SaludoResponse.newBuilder().
                setResultado("Hola "+ request.getNombre() + "por " + numeroSaludo + " vez" ).build();
                resObserver.onNext(respuesta);


        }
        resObserver.onCompleted();

    }

    @Override
    public void enviaArchivoStream(SaludoRequest request, StreamObserver<SaludoResponse> responseObserver) {
        Scanner sc = new Scanner(ServidorImp.class.getResourceAsStream("/" + request.getNombre()));
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado(linea)
                .build();
            responseObserver.onNext(respuesta);

            System.out.print(".");
        }

        sc.close();
        responseObserver.onCompleted();
        System.out.println("\nArchivo enviado exitosamente.");
           

    }

    
}
