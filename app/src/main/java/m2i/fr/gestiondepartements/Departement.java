package m2i.fr.gestiondepartements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Administrateur on 19/01/2018.
 */

public class Departement {

    private SQLiteDatabase db;

    private String noDept, nom, nomStd, dateCreation, chefLieu, urlWiki;
    private int noRegion, surface;

    private boolean depExisctant;

    final String TABLE_NAME = "departements";
    final String COLUMNS_Depart[] = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
    final String COLUMNS_Region[] = {"no_region", "nom"};

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) {
        this.noDept = noDept;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomStd() {
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }


    public Departement(Context ctxt) {

        DbInit dbInit = DbInit.getInstance(ctxt);
        db = dbInit.getWritableDatabase(); //initialisation de la bdd

    }

    public Departement(Context c, String numDept) {

        DbInit dbInit = DbInit.getInstance(c);
        db = dbInit.getWritableDatabase(); //initialisation de la bdd

        try {
            select(numDept);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select(String numDept) throws Exception {
        this.noDept = numDept;
        String where = "no_dept = '" + numDept + "'";
        Cursor cursor = db.query(TABLE_NAME, COLUMNS_Depart, where, null, null, null, null, null);

        //cursor.getCount() est le nombre d'éléments trouvé lors du query
        //si supérieur à 0 donc trouvé,

        System.out.println("getcount: " + cursor.getCount());
        if (cursor.getCount() == 1) {
            depExisctant = true;
            cursor.moveToFirst();
            this.noDept = cursor.getString(0);
            this.noRegion = cursor.getInt(1);
            this.nom = cursor.getString(2);
            this.nomStd = cursor.getString(3);
            this.surface = cursor.getInt(4);
            this.dateCreation = cursor.getString(5);
            this.chefLieu = cursor.getString(6);
            this.urlWiki = cursor.getString(7);

        } else {
            this.depExisctant = false;
        }

    }

    public void delete() throws Exception {

        String where = "no_dept ='" + this.noDept + "'";
        if (this.noDept.equals("")) {
            //exception
        } else {
            db.delete(TABLE_NAME, where, null);
        }
    }

    public void update() throws Exception {

        if (this.noDept.equals("")) {
            //exception
        } else {
            //ContentValues tableau de clé - valeur
            ContentValues values = new ContentValues();
            values.put("no_dept", this.noDept);
            values.put("no_region", this.noRegion);
            values.put("nom", this.nom);
            values.put("nom_std", this.nomStd);
            values.put("surface", this.surface);
            values.put("date_creation", this.dateCreation);
            values.put("chef_lieu", this.chefLieu);
            values.put("url_wiki", this.urlWiki);
            String where = "no_dept ='" + this.noDept + "'";
            db.update(TABLE_NAME, values, where, null);
        }
    }

    public void insert() throws Exception {

        if (this.noDept.equals("")) {
            //exception
        } else {
            ContentValues values = new ContentValues();
            values.put("no_dept", this.noDept);
            values.put("no_region", this.noRegion);
            values.put("nom", this.nom);
            values.put("nom_std", this.nomStd);
            values.put("surface", this.surface);
            values.put("date_creation", this.dateCreation);
            values.put("chef_lieu", this.chefLieu);
            values.put("url_wiki", this.urlWiki);
            db.insert(TABLE_NAME, null, values);

        }
    }


    public boolean getDepExisctant() {
        return depExisctant;
    }

    public void setDepExisctant(boolean depExisctant) {
        this.depExisctant = depExisctant;
    }
}