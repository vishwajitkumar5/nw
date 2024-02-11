import groovy.json.JsonSlurper
import groovy.json.JsonOutput

// Read values from JMeter variables
def ueid = vars.get("UEID")
def cqi = vars.get("CQI")
def cellID = vars.get("CellID")
def phyCellID = vars.get("PhyCellID")
def phyCellID_1 = vars.get("PhysicalCellId_1")
def phyCellID_2 = vars.get("PhysicalCellId_2")
def prbUsage = vars.get("PRBUsage")
def prbUsage_1 = vars.get("PRBUsage_1")
def prbUsage_2 = vars.get("PRBUsage_2")
def radioCondition = vars.get("RadioCondition")
def radioCondition_1 = vars.get("RadioCondition_1")
def radioCondition_2 = vars.get("RadioCondition_2")

// Path to your condition JSON file
def conditionFile1 = new File("C:/Users/vishwaku/indication/final/kpmcondition.json")
def conditionData1 = new JsonSlurper().parseText(conditionFile1.text)

// Get values from condition JSON based on radio condition
def conditionValues1 = conditionData1[cqi]

// Path to your JSON file to be updated
def kpmUEMeasurementsjsonFile = new File("C:/Users/vishwaku/indication/final/kpmUEMeasurements.json")
def kpmjsonData = new JsonSlurper().parseText(kpmUEMeasurementsjsonFile.text)

// Update JSON with matched condition values
// Update JSON with new value
kpmjsonData.ueMeasDataItem[0].MeasRecord[0].Integer = conditionValues1.Int
kpmjsonData.ueMeasDataItem[1].MeasRecord[0].Integer = conditionValues1.Int
kpmjsonData.measCondUEItem[0].matchingUEItem[0].GNBCUUEF1APID = ueid.toInteger()

// Write updated JSON to file
kpmUEMeasurementsjsonFile.write(JsonOutput.prettyPrint(JsonOutput.toJson(kpmjsonData)))


//chnages for rrcMeasurementReport
// Path to your JSON file
def rrcMeasurementReport = new File("C:/Users/vishwaku/indication/final/rrcMeasurementReport.json")
def rrcjsonData = new JsonSlurper().parseText(rrcMeasurementReport.text)

rrcjsonData.rrcMeasReport.MeasResultServingMOList[0].ServCellId = cellID.toInteger()
rrcjsonData.rrcMeasReport.MeasResultServingMOList[0].PhysCellId = phyCellID.toInteger()

rrcMeasurementReport.write(JsonOutput.prettyPrint(JsonOutput.toJson(rrcjsonData)))


// Path to your condition JSON file
def conditionFile2 = new File("C:/Users/vishwaku/indication/final/condition.json")
def conditionData2 = new JsonSlurper().parseText(conditionFile2.text)

// Get values from condition JSON based on radio condition
def conditionValues2 = conditionData2[radioCondition]

// Path to your JSON file to be updated
def rrc_condition_jsonFile = new File("C:/Users/vishwaku/indication/final/rrcMeasurementReport.json")
def rrc_condition_jsonData = new JsonSlurper().parseText(rrc_condition_jsonFile.text)

rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[0].PhysCellId = phyCellID_1.toInteger()
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[1].PhysCellId = phyCellID_2.toInteger()

// Update JSON with matched condition values
rrc_condition_jsonData.rrcMeasReport.MeasResultServingMOList[0].Rsrp = conditionValues2.Rsrp
rrc_condition_jsonData.rrcMeasReport.MeasResultServingMOList[0].Rsrq = conditionValues2.Rsrq
rrc_condition_jsonData.rrcMeasReport.MeasResultServingMOList[0].Sinr = conditionValues2.Sinr

// Get values from condition JSON based on radio condition
def conditionValues3 = conditionData2[radioCondition_1]

// Update JSON with matched condition values
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[0].Rsrp = conditionValues3.Rsrp
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[0].Rsrq = conditionValues3.Rsrq
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[0].Sinr = conditionValues3.Sinr


def conditionValues4 = conditionData2[radioCondition_2]

rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[1].Rsrp = conditionValues4.Rsrp
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[1].Rsrq = conditionValues4.Rsrq
rrc_condition_jsonData.rrcMeasReport.MeasResultListNR[1].Sinr = conditionValues4.Sinr

// Write updated JSON to file
rrc_condition_jsonFile.write(JsonOutput.prettyPrint(JsonOutput.toJson(rrc_condition_jsonData)))


// Path to your condition JSON file
def conditionFile3 = new File("C:/Users/vishwaku/indication/final/prbusages.json")
def conditionData3 = new JsonSlurper().parseText(conditionFile3.text)

// Get values from condition JSON based on radio condition
def conditionValues5 = conditionData3[prbUsage]

// Path to your JSON file to be updated
def kpm_condition_jsonFile = new File("C:/Users/vishwaku/indication/final/kpmCellMeasurementsFormat1.json")
def kpm_condition_jsonData = new JsonSlurper().parseText(kpm_condition_jsonFile.text)

def measRecord = kpm_condition_jsonData.cellMeasDataItem[0].MeasRecord

measRecord.eachWithIndex { record, index ->
    record.Integer = conditionValues5.Integer[index]
}

/*
// Get values from condition JSON based on radio condition
def conditionValues6 = conditionData3[prbUsage_1]
def measRecord2 = kpm_condition_jsonData.cellMeasDataItem[1].MeasRecord
measRecord2.eachWithIndex { record, index ->
    record.Integer = conditionValues6.Integer[index]
}


// Get values from condition JSON based on radio condition
def conditionValues7 = conditionData3[prbUsage_2]
def measRecord4 = kpm_condition_jsonData.cellMeasDataItem[2].MeasRecord
measRecord4.eachWithIndex { record, index ->
    record.Integer = conditionValues7.Integer[index]
}
*/
kpm_condition_jsonFile.write(JsonOutput.prettyPrint(JsonOutput.toJson(kpm_condition_jsonData)))
