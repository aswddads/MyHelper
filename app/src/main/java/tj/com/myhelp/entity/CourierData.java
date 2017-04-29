package tj.com.myhelp.entity;

/**
 * Created by Jun on 17/4/29.
 * 查询快递实体类
 */

public class CourierData {
//    时间
    private String datetime;
//    状态
    private String remark;
//    城市
    private String zone;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
