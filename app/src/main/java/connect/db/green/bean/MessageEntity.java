package connect.db.green.bean;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "MESSAGE_ENTITY".
 */
@Entity
public class MessageEntity implements java.io.Serializable {

    @Id(autoincrement = true)
    private Long _id;

    @NotNull
    private String message_ower;

    @NotNull
    @Unique
    private String message_id;
    private String content;
    private Long read_time;
    private Integer state;
    private Integer send_status;
    private Long snap_time;
    private Long createtime;

    @Generated
    public MessageEntity() {
    }

    public MessageEntity(Long _id) {
        this._id = _id;
    }

    @Generated
    public MessageEntity(Long _id, String message_ower, String message_id, String content, Long read_time, Integer state, Integer send_status, Long snap_time, Long createtime) {
        this._id = _id;
        this.message_ower = message_ower;
        this.message_id = message_id;
        this.content = content;
        this.read_time = read_time;
        this.state = state;
        this.send_status = send_status;
        this.snap_time = snap_time;
        this.createtime = createtime;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @NotNull
    public String getMessage_ower() {
        return message_ower;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setMessage_ower(@NotNull String message_ower) {
        this.message_ower = message_ower;
    }

    @NotNull
    public String getMessage_id() {
        return message_id;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setMessage_id(@NotNull String message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRead_time() {
        return read_time;
    }

    public void setRead_time(Long read_time) {
        this.read_time = read_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSend_status() {
        return send_status;
    }

    public void setSend_status(Integer send_status) {
        this.send_status = send_status;
    }

    public Long getSnap_time() {
        return snap_time;
    }

    public void setSnap_time(Long snap_time) {
        this.snap_time = snap_time;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

}
