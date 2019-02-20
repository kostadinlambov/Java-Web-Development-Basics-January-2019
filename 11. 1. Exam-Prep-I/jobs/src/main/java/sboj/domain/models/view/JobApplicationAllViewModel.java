package sboj.domain.models.view;

import sboj.domain.entities.Sector;


public class JobApplicationAllViewModel {
    private String id;
    private Sector sector;
    private String profession;

    public JobApplicationAllViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
