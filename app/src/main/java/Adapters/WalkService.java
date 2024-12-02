package Adapters;

public class WalkService {
    private String serviceName;
    private String petName;
    private String duration;
    private String serviceDate;

    public WalkService(String serviceName, String petName, String duration, String serviceDate) {
        this.serviceName = serviceName;
        this.petName = petName;
        this.duration = duration;
        this.serviceDate = serviceDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }
}
