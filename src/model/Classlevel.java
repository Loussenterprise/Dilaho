/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author loussin
 */
public class Classlevel {
    private Integer id;
    private String name;
    private String n;
    private String niveau;
    private String option;
    private String op;
    private Double contribution;
    
    public final static Classlevel MATERNELLE_I = new Classlevel("Mle","Maternelle I","Maternelle",null);
    public final static Classlevel MATERNELLE_II = new Classlevel("Mle","Maternelle II","Maternelle",null);
    public final static Classlevel MATERNELLE_III = new Classlevel("Mle","Maternelle III","Maternelle",null);
    
    public final static Classlevel CI = new Classlevel("CI","Cour d'Initiation","I",null);
    public final static Classlevel CP = new Classlevel("CP","Cour préparatoire","I",null);
    public final static Classlevel CE1 = new Classlevel("CE1","Cour Elementaire Première Année","II",null);
    public final static Classlevel CE2 = new Classlevel("CE2","Cour Elementaire Deuxième Année","II",null);
    public final static Classlevel CM1 = new Classlevel("CM1","Cour Moyen Première Année","III",null);
    public final static Classlevel CM2 = new Classlevel("CM2","Cour Moyen Deuxième Année","III",null);
    
    public final static Classlevel SEPTIEME = new Classlevel("7ème","Septième","---",null);
    public final static Classlevel SIXIEME = new Classlevel("6ème","Sixième","Premier Cycle",null);
    public final static Classlevel CINQUIEME = new Classlevel("5ème","Cinquième","Premier Cycle",null);
    
    public final static Classlevel QUATRIEME = new Classlevel("4ème","Quatrième","Premier Cycle",null);
    public final static Classlevel QUATRIEME_MC = new Classlevel("4ème","Quatrième","Premier Cycle",null,"Moderne Court","MC");
    public final static Classlevel QUATRIEME_ML = new Classlevel("4ème","Quatrième","Premier Cycle",null,"Moderne Long","ML");
    
    public final static Classlevel TROISIEME = new Classlevel("3ème","Troisième","Premier Cycle",null);
    public final static Classlevel TROISIEME_MC = new Classlevel("3ème","Troisième","Premier Cycle",null,"Moderne Court","MC");
    public final static Classlevel TROISIEME_ML = new Classlevel("3ème","Troisième","Premier Cycle",null,"Moderne Long","ML");
    
    public final static Classlevel SECONDE = new Classlevel("2nde","Seconde","Second Cycle",null);
    public final static Classlevel SECONDE_A = new Classlevel("2nde","Seconde","Second Cycle",null,"Série A","A");
    public final static Classlevel SECONDE_A1 = new Classlevel("2nde","Seconde","Second Cycle",null,"Série A1","A1");
    public final static Classlevel SECONDE_A2 = new Classlevel("2nde","Seconde","Second Cycle",null,"Série A2","A2");
    public final static Classlevel SECONDE_B = new Classlevel("2nde","Seconde","Second Cycle",null,"Série B","B");
    public final static Classlevel SECONDE_C = new Classlevel("2nde","Seconde","Second Cycle",null,"Série C","C");
    public final static Classlevel SECONDE_D = new Classlevel("2nde","Seconde","Second Cycle",null,"Série D","D");
    
    public final static Classlevel PREMIERE = new Classlevel("1ère","Première","Second Cycle",null);
    public final static Classlevel PREMIERE_A = new Classlevel("1ère","Première","Second Cycle",null,"Série A","A");
    public final static Classlevel PREMIERE_A1 = new Classlevel("1ère","Première","Second Cycle",null,"Série A1","A1");
    public final static Classlevel PREMIERE_A2 = new Classlevel("1ère","Première","Second Cycle",null,"Série A2","A2");
    public final static Classlevel PREMIERE_B = new Classlevel("1ère","Première","Second Cycle",null,"Série B","B");
    public final static Classlevel PREMIERE_C = new Classlevel("1ère","Première","Second Cycle",null,"Série C","C");
    public final static Classlevel PREMIERE_D = new Classlevel("1ère","Première","Second Cycle",null,"Série D","D");
    
    public final static Classlevel TERMINALE = new Classlevel("Tle","Terminale","Second Cycle",null);
    public final static Classlevel TERMINALE_A = new Classlevel("Tle","Terminale","Second Cycle",null,"Série A","A");
    public final static Classlevel TERMINALE_A1 = new Classlevel("Tle","Terminale","Second Cycle",null,"Série A1","A1");
    public final static Classlevel TERMINALE_A2 = new Classlevel("Tle","Terminale","Second Cycle",null,"Série A2","A2");
    public final static Classlevel TERMINALE_B = new Classlevel("Tle","Terminale","Second Cycle",null,"Série B","B");
    public final static Classlevel TERMINALE_C = new Classlevel("Tle","Terminale","Second Cycle",null,"Série C","C");
    public final static Classlevel TERMINALE_D = new Classlevel("Tle","Terminale","Second Cycle",null,"Série D","D");
    
    
    public Classlevel(String n, String name, String niveau, Double contribution) {
        this.n = n;
        this.name = name;
        this.niveau = niveau;
        this.contribution = contribution;
    }

    public Classlevel(String n, String name, String niveau, Double contribution, String option, String op) {
        this.n = n;
        this.name = name;
        this.niveau = niveau;
        this.contribution = contribution;
        this.option = option;
        this.op = op;
    }
    
    public Classlevel(Classlevel classlevel) {
        this.name = classlevel.name;
        this.n = classlevel.n;
        this.niveau = classlevel.niveau;
        this.contribution = classlevel.contribution;
        this.option = classlevel.option;
        this.op = classlevel.op;
    }
        

    public Classlevel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Double getContribution() {
        return contribution;
    }

    public void setContribution(Double contribution) {
        this.contribution = contribution;
    }
    
    

    @Override
    public String toString() {
        return n+" "+op;
    }
    
    public static ArrayList<Classlevel> getClasses() {
        ArrayList<Classlevel> list = new ArrayList();
        
        list.add(MATERNELLE_I);
        list.add(MATERNELLE_II);
        list.add(MATERNELLE_III);
        list.add(CI);
        list.add(CP);
        list.add(CE1);
        list.add(CE2);
        list.add(CM1);
        list.add(CM2);
        list.add(SIXIEME);
        list.add(CINQUIEME);
        list.add(QUATRIEME);
        list.add(QUATRIEME_MC);
        list.add(QUATRIEME_ML);
        list.add(TROISIEME);
        list.add(TROISIEME_MC);
        list.add(TROISIEME_ML);
        list.add(SECONDE);
        list.add(SECONDE_A);
        list.add(SECONDE_A1);
        list.add(SECONDE_A2);
        list.add(SECONDE_B);
        list.add(SECONDE_C);
        list.add(SECONDE_D);
        list.add(PREMIERE);
        list.add(PREMIERE_A);
        list.add(PREMIERE_A1);
        list.add(PREMIERE_A2);
        list.add(PREMIERE_B);
        list.add(PREMIERE_C);
        list.add(PREMIERE_D);
        list.add(TERMINALE);
        list.add(TERMINALE_A);
        list.add(TERMINALE_A1);
        list.add(TERMINALE_A2);
        list.add(TERMINALE_B);
        list.add(TERMINALE_C);
        list.add(TERMINALE_D);
        return list;
    }

    
    public static void main(String[] args) {
        Classlevel c = new Classlevel(TERMINALE_C);
        c.setContribution(Double.NaN);
        System.out.println(c);
    }
    
}
