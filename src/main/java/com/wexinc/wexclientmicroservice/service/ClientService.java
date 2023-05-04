package com.wexinc.wexclientmicroservice.service;

import com.wexinc.wexclientmicroservice.model.InterfaceFiles;
import com.wexinc.wexclientmicroservice.model.OutIntRequest;
import com.wexinc.wexclientmicroservice.model.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    RestTemplate template;

    public void transform(OutIntRequest request) throws Exception{
        //Here the file gets created and returns a notification to gateway
        //the file name, folder comes from the K/V store
        BufferedWriter writer = new BufferedWriter(new FileWriter("C://Ram/myrepo/clientfiles/" + request.getInterfaceID() + ".txt"));
        writer.write("This is test file created by WEX Client Microservice"+request.getUniqueIdentifier());
        writer.close();
        ProcessResponse response = new ProcessResponse();
        List<InterfaceFiles> fileList = new ArrayList<InterfaceFiles>();
        InterfaceFiles files =  new InterfaceFiles();
        files.setFileName(request.getInterfaceID() + ".txt");
        files.setFilePath("C://Ram/myrepo/clientfiles/" + request.getInterfaceID() + ".txt");
        fileList.add(files);
        response.setInputFiles(fileList);
        response.setInterfaceID(request.getInterfaceID());
        response.setClientId(request.getClientId());
        response.setUniqueIdentifier(request.getUniqueIdentifier());
        response.setResult("Success");
        //call the rest end point
        template.postForObject("http://wex-gateway-microservice/wexgateway/notify", response,  String.class) ;

    }
}
