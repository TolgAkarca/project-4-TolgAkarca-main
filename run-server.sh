#!/usr/bin/bash
main_dir=$PWD
if [[ -d $main_dir/build && -f $main_dir/build/Server.class ]]; then
   cd $main_dir/build/
   java -cp "$main_dir/dependencies/commons-cli-1.7.0.jar::$main_dir/dependencies/library.jar" Server "$@"
   exit 0
fi
echo "Server is not built!!"
exit 1
