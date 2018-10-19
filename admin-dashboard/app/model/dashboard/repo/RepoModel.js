Ext.define('Admin.model.dashboard.repo.RepoModel', {
    extend: 'Admin.model.Base',
    requires: [
        'Ext.data.proxy.Rest',
    ],
    fields: [
        { type: 'int', name: 'id' },
        { type: 'string', name: 'repoName' },
        { type: 'string', name: 'repoPhone' },
        { type: 'date', name: 'buildDate', dateFormat: 'Y/m/d' },
        { type: 'string', name: 'address' },
        { type: 'int', name: 'maxSize' },
        { type: 'int', name: 'minSize' },
        { type: 'string', name: 'status' },
        { type: 'string', name: 'type' },
        { type: 'int', name: 'workNum' ,mapping:'user.workNum'},
        { type: 'string', name: 'userRealName' ,mapping:'user.userRealName'}
    ],

    proxy: {
        type: 'rest',
        url: '/repo',
    }
});
