package grpchelloworld.servidor;

import com.proto.saludo.Holamundo.SaludoRequest;
import com.proto.saludo.Holamundo.SaludoResponse;
import com.proto.saludo.SaludoServiceGrpc;

import io.grpc.stub.StreamObserver;

public class ServidorImp extends SaludoServiceGrpc.SaludoServiceImplBase {
    
    @Override
    public void saludo(SaludoRequest request, StreamObserver<SaludoResponse> responObserver){
        //se construye la respuesta a enviar al cliente
        SaludoResponse respuesta = SaludoResponse.newBuilder().setResultado("Hola "+ request.getNombre()).build();

        //enviar la respuesta
        responObserver.onNext(respuesta);

        //avisar que ha terminado
        responObserver.onCompleted();

    }

}
