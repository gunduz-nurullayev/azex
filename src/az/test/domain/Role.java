package az.test.domain;

import az.test.domain.enums.Status;

import java.io.Serializable;

public class Role extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 4314315346065246895L;
    private String name;
    private String defaultController;
    private String defaultPage;

    public String getDefaultController() {  
        return defaultController;
    }

    public void setDefaultController(String defaultController) {
        this.defaultController = defaultController;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(String defaultPage) {
        this.defaultPage = defaultPage;
    }

    public Role() {
        super(0, Status.ACTIVE);
        this.name="";
        this.defaultController="";
        this.defaultPage="";
        
    }

    public Role(long id, String name) {
        super(id,Status.ACTIVE);
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\''+
                ", defaultController='" + defaultController + '\''+
                ", defaultPage='" + defaultPage + '\''+
                ", status=" + status +
                '}';
    }
}
