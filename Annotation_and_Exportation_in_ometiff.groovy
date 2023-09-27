import qupath.lib.gui.scripting.QPEx
import qupath.lib.gui.viewer.QuPathViewer
import qupath.lib.objects.PathAnnotationObject
import qupath.lib.objects.classes.PathClassFactory
import qupath.lib.objects.PathObject
import qupath.lib.roi.RectangleROI
import qupath.lib.images.servers.*
import qupath.lib.images.servers.PixelCalibration
import java.awt.image.BufferedImage
import java.lang.CharSequence
import java.io.File
import javax.imageio.*
import javax.imageio.ImageIO
import qupath.lib.images.writers.ome.OMEPyramidWriter
import qupath.lib.images.servers.ImageServerMetadata

//To be run on each (combined or not) images of a project
//Export all annotation of the current image to a specified project

//Previously done:
//Write out each region corresponding to an unclassified annotation
//Only use this if you have created SMALL unclassified annotations

//------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------ANNOTATIONS----------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------
//Script:
def imageName = GeneralTools.getNameWithoutExtension(getCurrentImageData().getServer().getMetadata().getName())
print("Exporting " + imageName + " Annotation...")
//Make sure the location you want to save the files to exists - requires a Project
def pathOutput = buildFilePath('U:/Biopsy_Stroma/Biopsies_stroma/Pancreatitis_tifs')
//def pathOutput = buildFilePath('U:/Biopsy Stroma/Biopsies_stroma/Cancer_tifs')
mkdirs(pathOutput)

def annotatedTiles = getAnnotationObjects()

for (tiles in annotatedTiles) {
    double downsample = 1 // Change Downsample Accordingly
    def tileROI = tiles.getROI()
    fileName = pathOutput+"\\"+imageName+ "_region_"+tiles+".tif"
    def requestROI = RegionRequest.createInstance(getCurrentServer().getPath(), downsample, tileROI)
    writeImageRegion(getCurrentServer(), requestROI, fileName)
}
// Get annotation names
//unclassifiedAnnotations = getAnnotationObjects().findAll{it.getPathClass() == null}
//03000664-00777735-22HI057022-1-A01-1 - 03000664-00803102-22HI058145-1-A01-1 - 02000664-00695985-22HI049592-1-A01-1
// for each annotation - it creates the corresponding image
//unclassifiedAnnotations.eachWithIndex{anno,x->
    //Name of the file and the path to where it goes in the Project
//    fileName = pathOutput+"//"+imageName+ "_region_"+unclassifiedAnnotations[x]+".tif" 
    //For each annotation, we get its outline
//    def roi = anno.getROI()
    //For each outline, we request the pixels within the bounding box of the annotation
//    def requestROI = RegionRequest.createInstance(getCurrentServer().getPath(), 1, roi)
    //The 1 in the function above is the downsample, increase it for smaller images
//    writeImageRegion(getCurrentServer(), requestROI, fileName)
//}
print("Annotation " + imageName + " Complete!")