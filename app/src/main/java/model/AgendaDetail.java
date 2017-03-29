package model;

/**
 * Created by AkmalJaved on 3/9/2017.
 */
public class AgendaDetail {
    private int type;
    private Chairman chairman;
    private Agenda agenda;
    private Topics topics;
    private String headerName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Chairman getChairman() {
        return chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
