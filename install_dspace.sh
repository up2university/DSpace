echo "Deleting backup files..."
rm -rf /home/user/dspace/*.bak*

echo "Cleaning and rebuilding..."
mvn clean package

echo "Copy DSpace to installation path..."
cd dspace/target/dspace-installer/ 
ant update

