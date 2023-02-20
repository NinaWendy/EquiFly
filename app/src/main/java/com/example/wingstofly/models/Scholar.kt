package com.example.wingstofly.models

import com.example.wingstofly.utils.Constants

class Scholar: java.io.Serializable{
    var name:String? = null; var status:String? = null
    var pfNumber: String?  = null
    var primaryNumber: String? = null
    var otherNumber: String? = null
    var emailAddress: String? = null
    var primarySchool: String? = null
    var primaryMeanGrade: String? = null
    var primaryMeanAGP: Int = 0
    var primaryMarks: Int = 0
    var secondarySchool: String? = null
    var varsity: String? = null
    var currentSubjects = ArrayList<Subject>()
    var meanGrade: String? = null
    var meanScore: Int = 0
    var meanAGP: Int = 0
    var courses = ArrayList<String>()
    var origin:String? = null
    var tertiaryInstitutions = ArrayList<String>()
    var password: String? = null
    private val agpValues = HashMap<String, Int>()

    constructor(){}
    constructor(name:String, status:String){
        this.name = name
        this.status = status
    }

    fun addSubject(subject: Subject){
        subject.grade = assignGrades(subject.score)
        subject.agp = assignAGP(subject.grade!!)!!
        currentSubjects.add(subject)
        calculateMeanScore()
    }

    private fun assignGrades(marks: Int): String{

        agpValues["A"] = 12
        agpValues["A-"] = 11
        agpValues["B+"] = 10
        agpValues["B"] = 9
        agpValues["B-"] = 8
        agpValues["C+"] = 7
        agpValues["C"] = 6
        agpValues["C-"] = 5
        agpValues["D+"] = 4
        agpValues["D"] = 3
        agpValues["D-"] = 2
        agpValues["E"] = 1

        if(marks >= 79){
            return "A"
        }else if (marks in 75..78){
            return "A-"
        }else if (marks in 70..74){
            return "B+"
        }else if (marks in 65..69){
            return "B"
        }else if (marks in 60..64){
            return "B-"
        }else if (marks in 55..59){
            return "C+"
        }else if (marks in 45..54){
            return "C"
        }else if (marks in 40..44){
            return "C-"
        }else if (marks in 35..39){
            return "D+"
        }else if (marks in 30..34){
            return "D"
        }else if (marks in 25..29){
            return "C+"
        }
        return "E"
    }
    private fun assignAGP(grade: String): Int? {
        for (key in agpValues.keys){
            if (key == grade){
                return agpValues[key]
            }
        }
        return 0
    }
    private fun calculateMeanScore(){
        var totalMarks = 0
        val noOfSubjects:Int = currentSubjects.size

        for (subject in currentSubjects){
            totalMarks += subject.score!!
        }

        meanScore = totalMarks/noOfSubjects
        scholarMeanGrade()
        scholarMeanAGP()

    }

    private fun scholarMeanGrade(){
        meanGrade = assignGrades(meanScore)
    }

    private fun scholarMeanAGP(){
        meanAGP = assignAGP(meanGrade!!)!!
    }

    fun calculateDeviation(): Int{
        var deviation = 0
        deviation = Constants.CUT_POINT_OFF_POINTS - meanAGP
        return deviation
    }


}

class ScholarStatus{
    var highSchool: String = "high-school"
    var campus: String = "University scholar"
    var preCampus: String = "A post graduate"
}

class Subject: java.io.Serializable{
    var name: String? =null; var category: String? =null
    var grade: String? = null;
    var agp: Int = 0
    var score:Int = 0

    constructor(){}
    constructor(name: String, category: String){
      this.name = name
      this.category = category
    }
}