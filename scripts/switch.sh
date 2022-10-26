blue="/etc/nginx/site-enabled/blue"
green="/etc/nginx/site-enabled/green"

reloadNginx() {
  systemctl restart nginx
}

turnOffNginx() {
  pkill -f nginx & wait $!
}

switchToGreen() {
  echo "Activate Green"

  rm -rf $blue
  ln -s /etc/nginx/site-available/green $green

  turnOffNginx
  reloadNginx

  echo "Nginx reloaded"

  exit 0
}

switchToBlue() {
  echo "Activate Blue"

  rm -rf $green
  ln -s /etc/nginx/site-avaiable/blue $blue

  turnOffNginx
  reloadNginx

  echo "Nginx reloaded"

  exit 0
}

findActivate() {
  if [ -L $blue ]; then
    switchToGreen
  fi

  if [ -L $green ]; then
    switchToBlue
  fi

  echo "There is no active sites"
  switchToBlue
}

echo "Start deployment"
findActivate