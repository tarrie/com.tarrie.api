package io.tarrie.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
// import io.swagger.v3.oas.annotations.Parameter;
import io.tarrie.controller.Controller;
import io.tarrie.model.Entity;
import io.tarrie.model.consumes.AddUserToGroup;
import io.tarrie.Utility;
import io.tarrie.model.consumes.UserId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

// https://www.mkyong.com/tutorials/jax-rs-tutorials/
// http://localhost:8080/api/groups/members
//    * http://localhost:8080/api/groups/members search?query=hello
@Api(tags = "Groups endpoints")
@SwaggerDefinition(
    tags = {
      @Tag(name = "Groups endpoints", description = "Used to create, modify, and get groups")
    })
@Path("/groups")
public class Groups implements io.tarrie.api.interfaces.Groups {


    // ToDo: Make sure u use the userId to find out if person has access
  @Path("{groupId}/events")
  @GET
  public Response listGroupEvents(
      @ApiParam(name = "groupId", value = "ID of group", required = true) @PathParam("groupId")
          String groupId,
      @DefaultValue("-1")
          @ApiParam(name = "startTime", value = "start time in (ISO 8601 format)")
          @QueryParam("startTime")
          String startDateTimeString,
      @DefaultValue("-1")
          @ApiParam(name = "endTime", value = "end time in (ISO 8601 format)")
          @QueryParam("endTime")
          String endDateTimeString,
      UserId requesterUserId) {

      String payload;

      if ((!(Utility.isIdValid(groupId))) || (!(Utility.isIdValid(requesterUserId.getUserId())))){
          return Response.status(400).entity("Bad input; groupid or userid is not valid").build();
      }

      try {
          payload = Utility.pojoToJson(Controller.getHostedEvents(groupId));
      } catch (JsonProcessingException e) {
          return Response.status(500).entity("Internal Server error; could not convert pojo to json").build();
      }
      return Response.status(200).entity(payload).build();

  }

  /*
  public Response doSomething(@Context HttpHeaders headers) {
    String accept = headers.getMediaType()
    List<MediaType> acceptableType = headers.getAcceptableMediaTypes();
    MediaType.

            image/gif, image/jpg, image/jpeg, image/png,
  }
  */
}