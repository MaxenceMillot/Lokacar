package com.eni.lokacar.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class Vehicule implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vehicule_id")
    private int id;
    private String modele;
    private String marque;
    private String plaque;
    private float prixJour;
    private String photo;
    private int nbPorte;
    private int nbPlace;
    private String carburant;
    private int critair;
    private boolean attelage;
    private boolean isCitadine;
    private boolean isDispo;

    public Vehicule(String modele, String marque, String plaque, float prixJour, String photo, int nbPorte, int nbPlace, String carburant, int critair, boolean attelage, boolean isCitadine, boolean isDispo) {
        this.modele = modele;
        this.marque = marque;
        this.plaque = plaque;
        this.prixJour = prixJour;
        this.photo = photo;
        this.nbPorte = nbPorte;
        this.nbPlace = nbPlace;
        this.carburant = carburant;
        this.critair = critair;
        this.attelage = attelage;
        this.isCitadine = isCitadine;
        this.isDispo = isDispo;
    }

    protected Vehicule(Parcel in) {
        id = in.readInt();
        modele = in.readString();
        marque = in.readString();
        plaque = in.readString();
        prixJour = in.readFloat();
        photo = in.readString();
        nbPorte = in.readInt();
        nbPlace = in.readInt();
        carburant = in.readString();
        critair = in.readInt();
        attelage = in.readByte() != 0;
        isCitadine = in.readByte() != 0;
        isDispo = in.readByte() != 0;
    }

    public static final Creator<Vehicule> CREATOR = new Creator<Vehicule>() {
        @Override
        public Vehicule createFromParcel(Parcel in) {
            return new Vehicule(in);
        }

        @Override
        public Vehicule[] newArray(int size) {
            return new Vehicule[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public float getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(float prixJour) {
        this.prixJour = prixJour;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNbPorte() {
        return nbPorte;
    }

    public void setNbPorte(int nbPorte) {
        this.nbPorte = nbPorte;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public int getCritair() {
        return critair;
    }

    public void setCritair(int critair) {
        this.critair = critair;
    }

    public boolean isAttelage() {
        return attelage;
    }

    public void setAttelage(boolean attelage) {
        this.attelage = attelage;
    }

    public boolean isCitadine() {
        return isCitadine;
    }

    public void setCitadine(boolean citadine) {
        isCitadine = citadine;
    }

    public boolean isDispo() {
        return isDispo;
    }

    public void setDispo(boolean dispo) {
        isDispo = dispo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(modele);
        parcel.writeString(marque);
        parcel.writeString(plaque);
        parcel.writeFloat(prixJour);
        parcel.writeString(photo);
        parcel.writeInt(nbPorte);
        parcel.writeInt(nbPlace);
        parcel.writeString(carburant);
        parcel.writeInt(critair);
        parcel.writeByte((byte) (attelage ? 1 : 0));
        parcel.writeByte((byte) (isCitadine ? 1 : 0));
        parcel.writeByte((byte) (isDispo ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", modele='" + modele + '\'' +
                ", marque='" + marque + '\'' +
                ", plaque='" + plaque + '\'' +
                ", prixJour=" + prixJour +
                ", photo='" + photo + '\'' +
                ", nbPorte=" + nbPorte +
                ", nbPlace=" + nbPlace +
                ", carburant='" + carburant + '\'' +
                ", critair=" + critair +
                ", attelage=" + attelage +
                ", isCitadine=" + isCitadine +
                ", isDispo=" + isDispo +
                '}';
    }
}
