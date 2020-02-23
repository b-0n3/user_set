package models;



import java.io.File;
import java.io.Serializable;

public class Staff implements Serializable {

	private int id ; //0
	private String birth_day;//1
	private String birth_place;//2
	private char  sex;//3
	private String cin; //ed 4
	private String sit_fam; //5
	private String nationalite ;//6
	private String date_embauche ;//7
	private String grade  ;//8
	private String functio_n ;//9
	private String post   ; ////10
	private String categorie ; //11
	private int echelon ;//12
	private String ent_effect ;//13;
	private String section_analytique; //ff 14
	private String regime_retraite;//15 //
	private int affil_recore ;////16
	private String date_der_promo; //17
	private File image;
	private static final long serialversionUID =
			1568630193381428614L;

	public Staff ()
	{
		setImage(new File("./defaultPerson.png"));
	}

	public Staff(int id,String birth_day,String birth_place,char  sex,String sit_fam,String nationalite,String date_embauche,String grade
			,String functio_n ,String post ,String categorie, int echelon ,String ent_effect,String regime_retraite, int affil_recore,String date_der_promo,String cin,String section_analytique) {

		this(id, birth_day , birth_place, sex, sit_fam, nationalite, date_embauche, grade,
				functio_n, post, categorie,echelon, ent_effect , regime_retraite, affil_recore, date_der_promo, new File("src/defualtPerson.png"),cin,section_analytique);


	}

	public Staff(int id,String birth_day,String birth_place,char  sex,String sit_fam,String nationalite,String date_embauche,String grade
			,String functio_n ,String post ,String categorie, int echelon ,String ent_effect,String regime_retraite, int affil_recore,String date_der_promo,File image,String cin,String section_analytique) {

		this.setId(id);
		this.setBirth_day(birth_day);
		this.setBirth_place(birth_place);
		this.setSex(sex);
		this.setSit_fam(sit_fam);
		this.setNationalite(nationalite);
		this.setDate_embauche(date_embauche);
		this.setGrade(grade);
		this.setFunctio_n(functio_n);
		this.setPost(post);
		this.setCategorie(categorie);
		this.setEchelon(echelon);
		this.setEnt_effect(ent_effect);
		this.setRegime_retraite(regime_retraite);
		this.setAffil_recore(affil_recore);
		this.setDate_der_promo(date_der_promo);
		this .setCin(cin);
		this.setSection_analytique(section_analytique);
		this.setImage(image);
	}


	public  boolean isNullOrEmpty(String str) {
		if(str != null && !str.trim().isEmpty() )
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirth_place() {
		if (isNullOrEmpty(this.birth_place))
			return ("NO VALUE");
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		if(isNullOrEmpty(birth_place))
			setBirth_place( "NO VALUE");
		else
			this.birth_place = birth_place;
	}

	public String getBirth_day() {
		return birth_day;
	}

	public void setBirth_day(String birth_day) {
		if(isNullOrEmpty(birth_day ))
			this.birth_day = "NO VALUE";
		else
			this.birth_day = birth_day;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		if( sex == ' ' )
			this.sex = '-';
		else
			this.sex = sex;
	}

	public String getNationalite() {

		return nationalite;
	}

	public void setNationalite(String nationalite) {
		if(isNullOrEmpty( nationalite ))
			this.nationalite = "NO VALUE";
		else
			this.nationalite = nationalite;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getDate_der_promo() {

		return date_der_promo;
	}

	public void setDate_der_promo(String date_der_promo) {
		if(isNullOrEmpty(date_der_promo))
			this.date_der_promo = "NO VALUE";
		else
			this.date_der_promo = date_der_promo;
	}

	public int getAffil_recore() {
		return affil_recore;
	}

	public void setAffil_recore(int affil_recore) {
		this.affil_recore = affil_recore;
	}

	public String getSit_fam() {
		return sit_fam;
	}

	public void setSit_fam(String sit_fam) {
		if(isNullOrEmpty(sit_fam))
			this.sit_fam = "NO VALUE";
		else
			this.sit_fam = sit_fam;
	}

	public String getDate_embauche() {
		return date_embauche;
	}

	public void setDate_embauche(String date_embauche) {
		if(isNullOrEmpty( date_embauche ))
			this.date_embauche = "NO VALUE";
		else
			this.date_embauche = date_embauche;

	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		if(isNullOrEmpty(grade ))
			this.grade = "NO VALUE";
		else
			this.grade = grade;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		if(isNullOrEmpty(post ))
			this.post = "NO VALUE";
		else
			this.post = post;
	}

	public String getFunctio_n() {
		return functio_n;
	}

	public void setFunctio_n(String functio_n) {
		if(isNullOrEmpty(functio_n ))
			this.functio_n = "NO VALUE";
		else
			this.functio_n = functio_n;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		if(isNullOrEmpty(categorie))
			this.categorie = "NO VALUE";
		else
			this.categorie = categorie;
	}

	public int getEchelon() {
		return echelon;
	}

	public void setEchelon(int echelon) {
		this.echelon = echelon;
	}

	public String getEnt_effect() {
		return ent_effect;
	}

	public void setEnt_effect(String ent_effect) {
		if(isNullOrEmpty(ent_effect ))
			this.ent_effect = "NO VALUE";
		else
			this.ent_effect = ent_effect;
	}

	public String getRegime_retraite() {

		return regime_retraite;
	}

	public void setRegime_retraite(String regime_retraite) {
		if(isNullOrEmpty(regime_retraite ))
			this.regime_retraite = "NO VALUE";
		else
			this.regime_retraite = regime_retraite;

	}
	public String toString()
	{
		return String.format("id = %d dsfadsfsd   grade %s bp =  %s  bd = %s  |\n", this.id, this.grade, this.birth_place , this.birth_day);
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getSection_analytique() {
		return section_analytique;
	}

	public void setSection_analytique(String section_analytique) {
		this.section_analytique = section_analytique;
	}
}
