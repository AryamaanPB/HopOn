package com.example.hopon;

public class naming {
    private String emailauth;
    private String namereq;
    private String mailreq;

    public naming(String emailauth, String namereq,String mailreq)
    {
        this.emailauth=emailauth;
        this.namereq=namereq;
        this.mailreq=mailreq;
    }

    public String getEmailauth() {
        return emailauth;
    }

    public String getNamereq() {
        return namereq;
    }

    public String getMailreq() {
        return mailreq;
    }
}
