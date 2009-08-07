
/**
 * Constructor for the UserModel class.
 * @constructor
 * @base CommonModel
 * @see CommonModel#initialize
 */
var UserModel = function() {
  this.initialize();
  this.persistedClassName = "fi.hut.soberit.agilefant.model.User";
  this.relations = {
    task: [],
    story: [],
    assignment: []
  };
  this.copiedFields = {
      "fullName": "fullName",
      "initials": "initials"
    };
};

UserModel.prototype = new CommonModel();

UserModel.prototype._setData = function(newData) {
  this.id = newData.id;
};

UserModel.prototype.getFullName = function() {
  return this.currentData.fullName;
};

UserModel.prototype.getInitials = function() {
  return this.currentData.initials;
};
