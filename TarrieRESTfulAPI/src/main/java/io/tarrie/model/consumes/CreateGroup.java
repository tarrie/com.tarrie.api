package io.tarrie.model.consumes;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.tarrie.database.contants.DbAttributes;
import io.tarrie.database.contants.DbConstants;
import io.tarrie.model.Location;
import io.tarrie.model.condensed.UserCondensed;
import io.tarrie.model.constants.CharacterLimit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.Date;

@DynamoDBTable(tableName = DbConstants.BASE_TABLE)
@ApiModel
public class CreateGroup {

    private String groupId;
    private String groupIdCopy;
    private String imgPath;
    private UserCondensed owner;
    private String name;
    private String bio;
    private Location location;
    private Date timeCreated;

    // partition key
    @DynamoDBHashKey(attributeName = DbAttributes.HASH_KEY)
    @ApiModelProperty(value = "the groupID (must be unique)")
    @NotNull
    @Size(min=1, max= CharacterLimit.SMALL)
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    // range key
    @DynamoDBRangeKey(attributeName=DbAttributes.SORT_KEY)
    @NotNull
    public String getGroupIdCopy() {
        return groupIdCopy;
    }
    public void setGroupIdCopy(String groupIdCopy) {
        this.groupIdCopy = groupIdCopy;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.CREATED_TIME)
    @DynamoDBAutoGeneratedTimestamp
    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }


    @DynamoDBAttribute(attributeName = DbAttributes.OWNER)
    @ApiModelProperty(value = "the user who is initiating the group creation. This user will be the owner")
    @NotNull
    public UserCondensed getOwner() {
        return owner;
    }
    public void setOwner(UserCondensed owner) {
        this.owner = owner;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.LOC)
    @ApiModelProperty(value = "The location of the group (Completely optional)" )
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.BIO)
    @Size(min=0, max=CharacterLimit.MEDIUM)
    @ApiModelProperty(value = "The biography of the new group")
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    @DynamoDBAttribute(attributeName = DbAttributes.NAME)
    @ApiModelProperty(value = "The name of the new group")
    @NotNull
    @Size(min=1, max= CharacterLimit.SMALL)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAutoGeneratedDefault(DbConstants.DEFAULT_GROUP_IMG)
    @DynamoDBAttribute(attributeName = DbAttributes.IMG_PATH)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "CreateGroup [ownerId=" + owner.getId() + ", groupName=" + name + ", groupHandle="+ groupId+"]";
    }
}
