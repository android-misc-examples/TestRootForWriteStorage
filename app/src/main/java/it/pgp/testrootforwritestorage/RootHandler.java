package it.pgp.testrootforwritestorage;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by pgp on 28/09/16
 * Web sources:
 * https://stackoverflow.com/questions/29750137/android-performing-su-commands-programatically-does-not-work
 * https://su.chainfire.eu/
 */

class RootHandler {

//    private static String[] rootCommand = {"su", "-c", ""};
//    private static String[] standardCommand = {"sh", "-c", ""};

    // ProcessBuilder is broken, command does not run
//    private static ProcessBuilder pb;
//    public static String executeCommand(String command, Map<String,String> envVars, boolean runAsSuperUser)
//    {
//        String[] fullCommand = runAsSuperUser?rootCommand.clone():standardCommand.clone();
//        fullCommand[2] = command;
//        pb = new ProcessBuilder(fullCommand);
//        Map<String, String> env = pb.environment();
//        if (envVars != null)
//            env.putAll(envVars);
//
//        StringBuilder output = new StringBuilder();
//
//        try {
//            // Process process = Runtime.getRuntime().exec(rootCommand);
//            Process process = pb.start();
//            process.waitFor();
//            Log.e(RootHandler.class.getName(),"Parent exited");
//
//            // no console output expected from process
////            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////
////            String line;
////            while ((line = reader.readLine())!= null) {
////                output.append(line).append("\n");
////            }
//
//        } catch (InterruptedException e) {
//            Log.e(RootHandler.class.getName(),"***InterruptedException***");
//            e.printStackTrace();
//        }
//        catch (IOException i) {
//            Log.e(RootHandler.class.getName(),"***IOException***");
//            i.printStackTrace();
//        }
//        return output.toString();
//    }

    static void executeCommandSimple(String command, File workingDir, boolean runAsSuperUser, String arg) {
//        String[] fullCommand = runAsSuperUser ? rootCommand.clone() : standardCommand.clone();
//        fullCommand[2] = command;

        String s = "";
//        if (arg == null)
//            s = fullCommand[0] + " " + fullCommand[1] + " " + fullCommand[2];
//        else
//            s = fullCommand[0] + " " + fullCommand[1] + " '" + fullCommand[2] + " "+ arg +"'";

//        if(runAsSuperUser) s += "su -c ";
        s += command;
        if (arg != null) s += " " + arg;

//        if (arg == null)
//            s = command;
//        else
//            s = command + " " + arg;

        Process p;
        try {
            if (runAsSuperUser) {
                p = Runtime.getRuntime().exec("su");
                DataOutputStream dos = new DataOutputStream(p.getOutputStream());
                if (workingDir != null) {
                    dos.writeBytes("cd " + workingDir +"\n");
                }
                dos.writeBytes(s + "\n");
                dos.writeBytes("exit\n");
                dos.flush();
                dos.close();
            } else {
                p = (workingDir==null)?
                        Runtime.getRuntime().exec(s):
                        Runtime.getRuntime().exec(s,null,workingDir)
                ;
            }
        }
        catch (IOException i) {
            Log.e(RootHandler.class.getName(),"***IOException");
//            return;
        }

//        try {
//            Process process;
//            if (workingDir == null) {
//                process = Runtime.getRuntime().exec(s);
//            }
//            else {
//                process = Runtime.getRuntime().exec(s,null,workingDir);
//            }
//
//            Log.e(RootHandler.class.getName(),"Executed:\n"+s+"\n");
//
//            int exitValue = process.waitFor();
//
//            StringBuilder output = new StringBuilder();
//            // no console output expected from process
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");
//            }
//
//            Log.d(RootHandler.class.getName(), "***BEGIN Parent process output:***\n" + output.toString() + "\n***END Parent process output***\nExit value: " + exitValue);
//
//        } catch (IOException i) {
//            Log.e(RootHandler.class.getName(), "***IOException***");
//            i.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
