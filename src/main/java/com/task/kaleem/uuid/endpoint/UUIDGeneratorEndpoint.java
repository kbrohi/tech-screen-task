package com.task.kaleem.uuid.endpoint;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.task.kaleem.uuid.domain.Response;
import com.task.kaleem.uuid.service.UUIDGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RestController
public class UUIDGeneratorEndpoint {

  UUIDGeneratorService uuidGeneratorService;

  public UUIDGeneratorEndpoint(UUIDGeneratorService uuidGeneratorService) {
    this.uuidGeneratorService = uuidGeneratorService;
  }

  @RequestMapping(value = "/uuid/v1/sequenceGenerator/{x}/{y}/{z}", method = RequestMethod.GET)
  @ApiOperation(value = "getReservationRetrieval", produces = MediaType.APPLICATION_JSON_VALUE,
      notes = "Service will return a UUID generator sequence ")
  @ResponseStatus(value = HttpStatus.OK)
  public Response getReservationRetrieval(@ApiParam("X") @PathVariable("x") Integer x,
      @ApiParam("Y") @PathVariable("y") Integer y, @ApiParam("Z") @PathVariable("z") Integer z) throws IOException {

    Response response;

    response = uuidGeneratorService.processUUIDGenerator(x, y, z);

    return response;
  }
}
