package com.wexinc.wexclientmicroservice.controller;

import com.wexinc.wexclientmicroservice.model.InterfaceFiles;
import com.wexinc.wexclientmicroservice.model.OutIntRequest;
import com.wexinc.wexclientmicroservice.model.ProcessResponse;
import com.wexinc.wexclientmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService service;

       @PostMapping(path = "/transform", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void invoke(@RequestBody OutIntRequest request) throws Exception {
           service.transform(request);
    }
}
