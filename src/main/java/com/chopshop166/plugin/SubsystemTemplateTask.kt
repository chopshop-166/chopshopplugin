package com.chopshop166.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

val subsystem_class = """package frc.robot.subsystems;

import com.chopshop166.chopshoplib.commands.SmartSubsystemBase;

import frc.robot.maps.subsystems.${'$'}{CLASSNAME}Map;

public class ${'$'}{CLASSNAME} extends SmartSubsystemBase {

    private ${'$'}{CLASSNAME}Map map;

    public ${'$'}{CLASSNAME}(final ${'$'}{CLASSNAME}Map map) {
        this.map = map;
    } 

    @Override
    public void reset() {
        // Nothing to reset here
    }

    @Override
    public void safeState() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // Use this for any background processing
    }
}
"""

val subsystem_map = """package frc.robot.maps.subsystems;

public class ${'$'}{CLASSNAME}Map {
    public ${'$'}{CLASSNAME}Map() {

    }
}
"""

abstract class SubsystemTask : DefaultTask() {
    
    @get:Option(option="name", description="name of subsystem to create")
    @get:Input
    abstract val subsystemName: String

    @Option(option="dir", description="directory for source code")
    @Input
    var robot_path: String = "src/main/java/frc/robot"

    @TaskAction
    fun addSubsystem() {
        logger.lifecycle("Creating subsystem for: ${subsystemName}")
        var className = ""
        for (part in subsystemName.split("\\s")) {
            className += part[0].toUpperCase() + part.substring(1)
        }
        val instanceName = className[0].toLowerCase() + className.substring(1)
        saveTemplate("maps/subsystems/${className}Map.java", subsystem_map, className)
        saveTemplate("subsystems/${className}.java", subsystem_class, className)
        insertInto("Robot.java", "\$Subsystems\$", "    ${className} ${instanceName} = new ${className}(map.get${className}Map());")
        insertInto("Robot.java", "\$Imports\$", "import frc.robot.subsystems.${className};")
        insertInto("maps/RobotMap.java", "\$Maps\$", "    private ${className}Map ${instanceName}Map = new ${className}Map();")
        insertInto("maps/RobotMap.java", "\$Getters\$", "    public ${className}Map get${className}Map() {\n        return ${instanceName}Map;\n    }")
    }

    fun saveTemplate(path : String, template : String, classname : String) {
        val final_path = robot_path + "/" + path
        var new_template = template.replace("\${CLASSNAME}", classname)
        project.file(final_path).writeText(new_template)
    }

    fun insertInto(filename : String, keyword : String, line : String) {
        val targetFile = project.file(robot_path + "/" + filename)
        val text = targetFile.readText()
        val replLine = "${keyword}\n${line}\n"
        targetFile.writeText(text.replace(keyword, replLine))
    }
}
