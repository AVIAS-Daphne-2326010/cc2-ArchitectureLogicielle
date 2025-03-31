package fr.univamu.iut.panier;

/**
 * Classe représentant un panier
 */
public class Panier {

    protected int id_type_panier;
    protected double prix;
    protected int n_panier_dispo;
    protected String mise_a_jour;

    public Panier() {}

    /**
     * Constructeur de panier
     * @param id_type_panier identifiant du type de panier
     * @param prix prix du panier
     * @param n_panier_dispo nombre de paniers disponibles
     * @param mise_a_jour date de mise à jour du panier
     */
    public Panier(int id_type_panier, double prix, int n_panier_dispo, String mise_a_jour) {
        this.id_type_panier = id_type_panier;
        this.prix = prix;
        this.n_panier_dispo = n_panier_dispo;
        this.mise_a_jour = mise_a_jour;
    }


    public int getId_type_panier() {
        return id_type_panier;
    }

    public void setId_type_panier(int id_type_panier) {
        this.id_type_panier = id_type_panier;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getN_panier_dispo() {
        return n_panier_dispo;
    }

    public void setN_panier_dispo(int n_panier_dispo) {
        this.n_panier_dispo = n_panier_dispo;
    }

    public String getMise_a_jour() {
        return mise_a_jour;
    }

    public void setMise_a_jour(String mise_a_jour) {
        this.mise_a_jour = mise_a_jour;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_type_panier=" + id_type_panier +
                ", prix=" + prix +
                ", n_panier_dispo=" + n_panier_dispo +
                ", mise_a_jour='" + mise_a_jour + '\'' +
                '}';
    }
}
