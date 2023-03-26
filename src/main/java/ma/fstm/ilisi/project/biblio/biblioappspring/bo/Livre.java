package ma.fstm.ilisi.project.biblio.biblioappspring.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="LIVRE"
        ,schema="BIBLIO2"
)
public class Livre implements java.io.Serializable {

    public Livre(String isbn, String titre) {
        this.isbn = isbn;
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }

    protected String isbn;
    protected String titre;
    // private Set<Exemplaire> exemplaires = new HashSet(0);

    public Livre() {
    }


    public Livre(String isbn) {
        this.isbn = isbn;
    }
  /*  public Livre(String isbn, String titre , Set exemplaires) {
        this.isbn = isbn;
        this.titre = titre;
        this.exemplaires = exemplaires;
    }*/

    @Id


    @Column(name="ISBN", unique=true, nullable=false, length=13)
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Column(name="TITRE", length=100)
    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
/*
    @OneToMany(fetch=FetchType.LAZY, mappedBy="livre")
    public Set<Exemplaire> getExemplaires() {
        return this.exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
*/



}


