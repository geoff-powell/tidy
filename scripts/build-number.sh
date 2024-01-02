#!/bin/bash
set -x

TAGS=$(git show-ref --tags)
GITHUB_SERVER_URL="https://github.com"
GITHUB_URL_PROTOCOL="$(echo "$GITHUB_SERVER_URL" | grep '://' | sed -e 's,^\(.*\)://.*,\1,g')"

function getBuildNumberTag {
  set +e
  echo "$TAGS"| grep "${1:-}" | grep -oE 'build-number-[[:digit:]]+' | grep -oE '[[:digit:]]+' | sort -nr | head -1
  set -e
}

CURRENT_BUILD_NUMBER=$(getBuildNumberTag "$GITHUB_SHA")

OUT=$(echo "$TAGS" | grep -oE 'build-number-[[:digit:]]+')
echo "$OUT"

if [[ -z "${CURRENT_BUILD_NUMBER}" ]]; then
  echo "No build number found for current SHA $GITHUB_SHA."

  # Get the latest build number
  LATEST_BUILD_NUMBER=$(getBuildNumberTag)

  if [[ -z "${LATEST_BUILD_NUMBER}" ]]; then
    echo "No build number found in tags. Please create a tag with the format 'build-number-1' on main branch."
    exit 1
  fi

  CURRENT_BUILD_NUMBER="$((LATEST_BUILD_NUMBER + 1))"
  git tag "build-number-$CURRENT_BUILD_NUMBER"

  git config --local user.email "github-actions[bot]@users.noreply.github.com"
  git config --local user.name "github-actions[bot]"

  GITHUB_URL_PROTOCOL=$(echo "$GITHUB_SERVER_URL" | sed -e 's,^\(.*://\).*,\1,g')
  GITHUB_URL_HOST=$(echo "$GITHUB_SERVER_URL" | sed -e 's,^.*://\(.*\),\1,g')

  echo "Pushing build number tag build-number-$CURRENT_BUILD_NUMBER to Github ($GITHUB_REF)."

  git push "${GITHUB_URL_PROTOCOL}${INPUT_GITHUB_TOKEN}@${GITHUB_URL_HOST}/${GITHUB_REPOSITORY}.git" --tags
else
  echo "Found build number $CURRENT_BUILD_NUMBER for current SHA $GITHUB_SHA."
  echo "Skipping tag creation."
fi

echo "BUILD_NUMBER=$CURRENT_BUILD_NUMBER" >> "$GITHUB_ENV"