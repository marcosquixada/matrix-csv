package com.matrix.api.controller;

import com.matrix.api.service.MatrixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MatrixController {

    private static final Logger log = LoggerFactory.getLogger(MatrixController.class);

    @Autowired
    MatrixService matrixService;

    /**
     * Echo the CSV file content.
     *
     * @param file
     * @return ResponseEntity<String>
     */
    @PostMapping("/echo")
    @Operation(summary = "Echo the CSV file content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo the CSV file content."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> echo(@RequestPart("file") MultipartFile file) {
        log.info("Echoing file: {}", file.getOriginalFilename());

        String result = matrixService.echo(file);
        if(!result.startsWith("Fail")) return ResponseEntity.ok(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * Sum the CSV file content.
     *
     * @param file
     * @return ResponseEntity<String>
     */
    @PostMapping("/sum")
    @Operation(summary = "Echo the CSV file content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo the CSV file content."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> sum(@RequestPart("file") MultipartFile file) {
        log.info("Summing file: {}", file.getOriginalFilename());

        Long result = matrixService.sum(file);
        if(-1L != result.longValue()) return ResponseEntity.ok(result + "");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error summing csv.");
    }

    /**
     * Multiplies the CSV file content.
     *
     * @param file
     * @return ResponseEntity<String>
     */
    @PostMapping("/multiply")
    @Operation(summary = "Echo the CSV file content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo the CSV file content."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> multiply(@RequestPart("file") MultipartFile file) {
        log.info("Multiplying file: {}", file.getOriginalFilename());

        Long result = matrixService.multiply(file);
        if(-1L != result.intValue()) return ResponseEntity.ok(result + "");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error multiplying csv.");
    }

    /**
     * Flatten the CSV file content.
     *
     * @param file
     * @return ResponseEntity<String>
     */
    @PostMapping("/flatten")
    @Operation(summary = "Echo the CSV file content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo the CSV file content."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> flatten(@RequestPart("file") MultipartFile file) {
        log.info("Flattening file: {}", file.getOriginalFilename());

        String result = matrixService.flatten(file);
        if(!result.startsWith("Fail")) return ResponseEntity.ok(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * Inverts the CSV file content.
     *
     * @param file
     * @return ResponseEntity<String>
     */
    @PostMapping("/invert")
    @Operation(summary = "Echo the CSV file content.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Echo the CSV file content."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> invert(@RequestPart("file") MultipartFile file) {
        log.info("Inverting file: {}", file.getOriginalFilename());

        String result = matrixService.invert(file);
        if(!result.startsWith("Fail")) return ResponseEntity.ok(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

}