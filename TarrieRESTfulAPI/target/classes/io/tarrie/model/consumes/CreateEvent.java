package io.tarrie.model.consumes;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.tarrie.Utility;
import io.tarrie.database.contants.DbAttributes;
import io.tarrie.database.contants.DbConstants;
import io.tarrie.database.exceptions.MalformedInputException;
import io.tarrie.model.EventPrivacy;
import io.tarrie.model.EventTime;
import io.tarrie.model.HashTag;
import io.tarrie.model.Location;
import io.tarrie.model.constants.CharacterLimit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@DynamoDBTable(tableName = DbConstants.BASE_TABLE)
@ApiModel
public class CreateEvent {

    private String creatorId;
    private String name;
    private EventPrivacy eventPrivacy;
    private Location location;
    // default img path
    private static final String imgPath = DbConstants.DEFAULT_EVENT_IMG;
    // default link sharing is turned off
    private static final boolean linkSharing=false;
    private List<String> invitedEntityIds;
    private String bio;
    private List<HashTag> hashTags;
    private String eventId;
    private String eventIdCopy;
    private String startTime;
    private String endTime;
    private Date createdTime;

    // partition key
    @DynamoDBHashKey(attributeName = DbAttributes.HASH_KEY)
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    // range key
    @DynamoDBRangeKey(attributeName = DbAttributes.SORT_KEY)
    public String getEventIdCopy() {
        return eventIdCopy;
    }
    public void setEventIdCopy(String eventIdCopy) {
        this.eventIdCopy = eventIdCopy;
    }

    @ApiModelProperty(value = "The start time of the event")
    @DynamoDBAttribute(attributeName = DbAttributes.DATA)
    @NotNull
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) throws MalformedInputException {
        Utility.isDateTimeValid(startTime);
        this.startTime = startTime;
    }

    @ApiModelProperty(value = "The created time of the event")
    @DynamoDBAttribute(attributeName = DbAttributes.CREATED_TIME)
    @DynamoDBAutoGeneratedTimestamp(strategy=DynamoDBAutoGenerateStrategy.CREATE)
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.END_TIME)
    @ApiModelProperty(value="The end time of the event")
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) throws MalformedInputException {
        Utility.isDateTimeValid(endTime);
        this.endTime = endTime;
    }

    @ApiModelProperty(value = "Link sharing boolean", hidden = true)
    @DynamoDBAttribute(attributeName = DbAttributes.LINK_SHARING)
    public boolean getLinkSharing() {return linkSharing;}

    @DynamoDBAttribute(attributeName = DbAttributes.CREATOR_ID)
    @ApiModelProperty(value = "The id of entity creating event")
    @NotNull
    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.NAME)
    @ApiModelProperty(value = "The title of the event")
    @Size(min=1, max= CharacterLimit.SMALL)
    @NotNull
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.EVENT_PRIVACY)
    @ApiModelProperty(notes = "The privacy specifications of the events")
    @NotNull
    public EventPrivacy getEventPrivacy() {
        return eventPrivacy;
    }
    public void setEventPrivacy(EventPrivacy eventPrivacy) {
        this.eventPrivacy = eventPrivacy;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.LOC)
    @ApiModelProperty(value = "The event location (if left empty the event is virtual")
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.IMG_PATH)
    @ApiModelProperty(value = "The profile pic of the event, if left empty a default pic is provided", hidden = true)
    public String getImgPath() {
        return imgPath;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.BIO)
    @ApiModelProperty(value = "The description of the event")
    @Size(min=0, max= CharacterLimit.LARGE)
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    // relationship -> ignore we treat this specially
    @DynamoDBIgnore
    @ApiModelProperty(value = "Collection of entityIds invited to event")
    public List<String> getInvitedEntityIds() {
        return invitedEntityIds;
    }
    public void setInvitedEntityIds(List<String> invitedEntityIds) {
        this.invitedEntityIds = invitedEntityIds;
    }

    // relationship -> ignore we treat this specially
    @DynamoDBIgnore
    @ApiModelProperty(notes = "hash tags associated with event")
    public List<HashTag> getHashTags() {
        return hashTags;
    }
    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

}
