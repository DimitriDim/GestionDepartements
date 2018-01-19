package m2i.fr.gestiondepartements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Departement dept;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtDateCreation;
    private EditText txtChefLieu;
    private EditText txtUrlWiki;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNoDept = findViewById(R.id.txtNoDept);
        txtNoRegion = findViewById(R.id.txtNoRegion);
        txtNom = findViewById(R.id.txtNom);
        txtSurface = findViewById(R.id.txtSurface);
        txtNomStd = findViewById(R.id.txtNomStd);
        txtDateCreation = findViewById(R.id.txtDateCreation);
        txtChefLieu = findViewById(R.id.txtChefLieu);
        txtUrlWiki = findViewById(R.id.txtUrlWiki);
        txtSearch = findViewById(R.id.txtSearch);

        dept = new Departement(this);
    }

    public void btnSearch(View view) {
        String depSearch = txtSearch.getText().toString();
        dept = new Departement(this, depSearch);
        txtNoDept.setText(dept.getNoDept());
        txtNoRegion.setText(String.valueOf(dept.getNoRegion()));
        txtSurface.setText(String.valueOf(dept.getSurface()));
        txtNom.setText(dept.getNom());
        txtNomStd.setText(dept.getNomStd());
        txtDateCreation.setText(dept.getDateCreation());
        txtChefLieu.setText(dept.getChefLieu());
        txtUrlWiki.setText(dept.getUrlWiki());
        txtSearch.setEnabled(false);

        if (dept.getDepExisctant() == false) {
            dept = new Departement(this, "");
            Toast.makeText(this, "Département inexistant", Toast.LENGTH_LONG).show();
        }

    }

    public void btnClear(View view) {
        txtSearch.setEnabled(true);
        dept = new Departement(this, "");
        txtNoDept.setText(dept.getNoDept());
        txtNoRegion.setText("");
        txtSurface.setText("");
        txtNom.setText(dept.getNom());
        txtNomStd.setText(dept.getNomStd());
        txtDateCreation.setText(dept.getDateCreation());
        txtChefLieu.setText(dept.getChefLieu());
        txtUrlWiki.setText(dept.getUrlWiki());

    }

    public void btnDelete(View view) {

        String depSearch = txtSearch.getText().toString();
        dept = new Departement(this, depSearch);
        try {
            dept.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnSave(View view) {
        String depTester = txtNoDept.getText().toString();
        try {
            dept.select(depTester);
            if (dept.getDepExisctant() == false) {

                dept.setNoDept(txtNoDept.getText().toString());
                dept.setNoRegion(Integer.parseInt(txtNoDept.getText().toString()));
                dept.setNom(txtNom.getText().toString());
                dept.setNomStd(txtNomStd.getText().toString());
                dept.setSurface(Integer.parseInt(txtSurface.getText().toString()));
                dept.setDateCreation(txtDateCreation.getText().toString());
                dept.setChefLieu(txtChefLieu.getText().toString());
                dept.setUrlWiki(txtUrlWiki.getText().toString());
                dept.insert();
                Toast.makeText(this, "Nouveau département enregistré !", Toast.LENGTH_LONG).show();
            } else {

                dept.setNoDept(txtNoDept.getText().toString());
                dept.setNoRegion(Integer.parseInt(txtNoDept.getText().toString()));
                dept.setNom(txtNom.getText().toString());
                dept.setNomStd(txtNomStd.getText().toString());
                dept.setSurface(Integer.parseInt(txtSurface.getText().toString()));
                dept.setDateCreation(txtDateCreation.getText().toString());
                dept.setChefLieu(txtChefLieu.getText().toString());
                dept.setUrlWiki(txtUrlWiki.getText().toString());
                dept.update();
                Toast.makeText(this, "Modification d'un departement existant !", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }
}
