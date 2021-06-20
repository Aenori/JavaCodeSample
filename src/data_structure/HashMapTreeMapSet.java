package data_structure;

import java.util.*;

public class HashMapTreeMapSet {

	public static void main(String[] args) {
		exampleSetHashAndTree();
		exampleMapHashAndTree();
		exampleCustomClass();
	}

	private static void exampleSetHashAndTree() {
		// Set est une interface. Vous ne pouvez pas faire de new Set;
		// Les caractéristiques d'un set sont :
		//   - les éléments sont uniques
		//   - il y a une fonction de recherche efficace (contains)
		// NB : ne pas utiliser la méthode contains sur une list, ou avec
		//	précaution
		Set<String> nameSet;

		System.out.println("## Exemple TreeSet ##");

		// On utilise l'implémentation TreeSet
		nameSet = new TreeSet<String>();
		nameSet.add("Julie");
		// Ne sera pas ajouté
		nameSet.add("Julie");
		nameSet.add("Charline");
		nameSet.add("Yasmine");

		// Affiche [Charline, Julie, Yasmine]
		System.out.println(Arrays.toString(nameSet.toArray()));

		System.out.println("## Exemple HashSet ##");

		// On utilise l'implémentation HashSet
		nameSet = new HashSet<String>();
		nameSet.add("Julie");
		// Ne sera pas ajouté
		nameSet.add("Julie");
		nameSet.add("Charline");
		nameSet.add("Yasmine");

		// Affiche [Julie, Yasmine, Charline]
		System.out.println(Arrays.toString(nameSet.toArray()));

		// Regle à retenir :
		// Il n'y a pas de grosse différence entre les deux, par défaut utiliser
		// hashSet, sauf si vous pouvez avoir besoin de les avoir triés
	}


	private static void exampleMapHashAndTree() {
		// Map est une interface. Vous ne pouvez pas faire de new map;
		// Les caractéristiques d'un map sont :
		//   - les clés sont uniques
		//   - il y a une fonction de recherche efficace (containsKey)
		// NB : ne pas utiliser la méthode contains sur une list, ou avec
		//	précaution
		Map<String, String> nameMap;

		System.out.println("## Exemple TreeMap ##");

		// On utilise l'implémentation TreeMap
		nameMap = new TreeMap<String, String>();
		nameMap.put("Julie", "Martin");
		// Ne sera pas ajouté
		nameMap.put("Julie", "Dupont");
		nameMap.put("Charline", "Doe");
		nameMap.put("Yasmine", "Reza");

		// Affiche [Charline=Doe, Julie=Dupont, Yasmine=Reza]
		System.out.println(Arrays.toString(nameMap.entrySet().toArray()));

		System.out.println("## Exemple HashMap ##");

		nameMap = new HashMap<String, String>();
		nameMap.put("Julie", "Martin");
		// Ne sera pas ajouté
		nameMap.put("Julie", "Dupont");
		nameMap.put("Charline", "Doe");
		nameMap.put("Yasmine", "Reza");

		// Affiche [Julie=Dupont, Yasmine=Reza, Charline=Doe]
		System.out.println(Arrays.toString(nameMap.entrySet().toArray()));

		// Regle à retenir :
		// Il n'y a pas de grosse différence entre les deux, par défaut utiliser
		// hashSet, sauf si vous pouvez avoir besoin de les avoir triés
	}

	private static class ClassForHash {
		private int id;

		public ClassForHash(int id) {
			this.id = id;
		}
	}

	private static class ClassForHash2 {
		private int id;

		public ClassForHash2(int id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			return this.id;
		}

		@Override
		public boolean equals(Object o) {
			// This will raise an exception if o is not a ClassForHash2
			return this.id == ((ClassForHash2)o).id;
		}
	}

	private static class ClassForTree implements Comparable<ClassForTree> {
		private int id;

		public ClassForTree(int id) {
			this.id = id;
		}

		@Override
		public int compareTo(ClassForTree o) {
			// TODO Auto-generated method stub
			return this.id - o.id;
		}
		
	}
	
	private static void exampleCustomClass() {
		exampleCustomClass1();
		exampleCustomClass2();
		exampleCustomClass3();
	}
	
	private static void exampleCustomClass1() {
		Set<ClassForHash> exemple1 = new HashSet<ClassForHash>();

		ClassForHash myObj1 = new ClassForHash(1);
		ClassForHash myObj1Same = new ClassForHash(1);

		exemple1.add(myObj1);
		exemple1.add(myObj1);
		exemple1.add(myObj1Same);

		// Affiche 2
		System.out.println("Nb elements in exemple1 : " + exemple1.size());
	}
	
	private static void exampleCustomClass2() {
		Set<ClassForHash2> exemple2 = new HashSet<ClassForHash2>();

		ClassForHash2 myObj2 = new ClassForHash2(1);
		ClassForHash2 myObj2Same = new ClassForHash2(1);

		exemple2.add(myObj2);
		exemple2.add(myObj2);
		exemple2.add(myObj2Same);

		// Affiche 1, myObj2 and myObj2Same are considered to be the same object
		System.out.println("Nb elements in exemple2 : " + exemple2.size());
		
		// But what happens with TreeSet ??
		try {
			exemple2 = new TreeSet<ClassForHash2>();
			exemple2.add(myObj2);
		}
		catch(ClassCastException e) {
			// Damn Java, why didn't you tell at compilation time that 
			// TreeSet<ClassForHash2>() is not valid ? 
			// 
			// NB : If you really want a strong typed langage, go for C++
			System.err.println(e.getMessage());
		}
	}
	
	// Cette fois ça marche ! Il faut que ClassForTree implement l'interface
	// Comparable.
	private static void exampleCustomClass3() {
		Set<ClassForTree> exemple3 = new TreeSet<ClassForTree>();

		ClassForTree myObj = new ClassForTree(1);
		ClassForTree myObjSame = new ClassForTree(1);

		exemple3.add(myObj);
		exemple3.add(myObj);
		exemple3.add(myObjSame);
		
		// Affiche 1, myObj and myObjSame are considered to be the same object
		System.out.println("Nb elements in exemple 3 : " + exemple3.size());
	}
}
